from django.shortcuts import render, redirect
from django.apps import apps
from GestionBaseDeDatos.forms import AddPersonaForm, UpdatePersonaForm
from django.views.decorators.csrf import csrf_protect
from GestionBaseDeDatos.models import Persona
import datetime


# Create your views here.
def home(request):
    app_models = apps.get_app_config('GestionBaseDeDatos').get_models()

    listaDiccionarios = []
    for model in app_models:
        listaAtributos = []
        listaValues = []
        values = getAll(model)
        for field in model._meta.get_fields():
            listaAtributos.append({"nombre": field.verbose_name.title(), "tipo": field.get_internal_type()})

        for value in values:
            listaValues.append({"id": value["id"], "valores": value.values(), "imagen": value["imagen"]})

        if len(values) != 0:
            listaDiccionarios.append({"nombre": model.__name__, "atributos": listaAtributos, "contieneValores": True,
                                      "modelos": listaValues})
        else:
            listaDiccionarios.append({"nombre": model.__name__, "atributos": listaAtributos, "contieneValores": False,
                                      "modelos": ""})

    return render(request, "home.html", {"etiquetasModelos": listaDiccionarios})


@csrf_protect
def addView(request):
    return render(request, "formulario.html",
                  {"formulario": AddPersonaForm(), "action": "/add", "title": "Add ", "id": "",
                   "nombre": str(request.POST.get('modelo'))})


@csrf_protect
def updateView(request):
    app_models = apps.get_app_config('GestionBaseDeDatos').get_models()
    nombre = str(request.POST.get("modelo")).split("&&")[0]
    id = str(request.POST.get("modelo")).split("&&")[1]
    for model in app_models:
        if (model.__name__ == nombre):
            modelo = model.objects.get(id=id)
            formulario = UpdatePersonaForm(instance=modelo)
            return render(request, "formulario.html",
                          {"formulario": formulario, "action": "/update",
                           "title": "Update ", "id": id,
                           "nombre": nombre})


@csrf_protect
def add(request):
    if request.method == "POST":
        formulario = AddPersonaForm(request.POST, request.FILES)
        if formulario.is_valid():
            # formulario.save()
            insertar(request)
            return redirect(home)


@csrf_protect
def update(request):
    nombre = str(request.POST).split(",")[len(str(request.POST).split(",")) - 1].split('\'')[1].split("&&")[0]
    id = str(request.POST).split(",")[len(str(request.POST).split(",")) - 1].split('\'')[1].split("&&")[1]
    if request.method == "POST" and not "Cancel" in str(request.POST):
        formulario = UpdatePersonaForm(request.POST, request.FILES)
        actualiza(request, id, nombre)

    return redirect(home)

@csrf_protect
def delete(request):
    app_models = apps.get_app_config('GestionBaseDeDatos').get_models()
    nombre = str(request.POST.get("modelo")).split("&&")[0]
    id = str(request.POST.get("modelo")).split("&&")[1]
    for model in app_models:
        if (model.__name__ == nombre):
            modelo = model.objects.get(id=id)
            modelo.delete()
            return redirect(home)




def getAll(model):
    return model.objects.all().values()


def insertar(request):
    usuario = request.POST.get("usuario")
    password = request.POST.get("password")
    name = request.POST.get("name")
    surname = request.POST.get("surname")
    companyEmail = request.POST.get("companyEmail")
    personalEmail = request.POST.get("personalEmail")
    active = True
    createdDate = datetime.date.today()
    imagen = request.POST.get("imagen")
    print(imagen)
    persona = Persona(usuario=usuario, password=password, name=name, surname=surname,
                      companyEmail=companyEmail, personalEmail=personalEmail, active=active,
                      createdDate=createdDate, imagen=imagen)
    persona.save()


def actualiza(request, id, nombre):
    model = Persona.objects.get(id=id)
    model.usuario = request.POST.get("usuario")
    model.password = request.POST.get("password")
    model.name = request.POST.get("name")
    model.surname = request.POST.get("surname")
    model.companyEmail = request.POST.get("companyEmail")
    model.personalEmail = request.POST.get("personalEmail")
    if (request.POST.get("active") == "on"):
        model.active = True
        model.terminationDate = None
    else:
        model.active = False
        model.terminationDate = datetime.date.today()
    model.imagen = request.FILES.get("imagen")
    model.save()
