from django import forms
from GestionBaseDeDatos.models import Persona


class AddPersonaForm(forms.ModelForm):
    class Meta:
        model = Persona
        exclude = ["id", "active", "createdDate", "terminationDate"]

    def __init__(self, *args, **kwargs):
        super(AddPersonaForm, self).__init__(*args, **kwargs)
        for visible in self.visible_fields():
            visible.field.widget.attrs['class'] = 'form-control'


class UpdatePersonaForm(forms.ModelForm):
    class Meta:
        model = Persona
        exclude = ["id", "createdDate", "terminationDate"]


    def __init__(self, *args, **kwargs):
        super(UpdatePersonaForm, self).__init__(*args, **kwargs)
        for visible in self.visible_fields():
            visible.field.widget.attrs['class'] = 'form-control'
