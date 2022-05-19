from django.contrib import admin

# Register your models here.
from GestionBaseDeDatos.models import Persona

class PesonaAdmin(admin.ModelAdmin):
    list_display = ("id","usuario","password","name","surname","companyEmail","personalEmail","active","createdDate","terminationDate","imagen")
    search_fields = ("usuario","name")
    list_filter = ("createdDate","terminationDate",)

admin.site.register(Persona,PesonaAdmin)