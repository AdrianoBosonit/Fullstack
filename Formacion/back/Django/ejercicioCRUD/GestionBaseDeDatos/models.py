from django.db import models


# Create your models here.
class Persona(models.Model):
    id = models.AutoField(primary_key=True)
    usuario = models.CharField(max_length=60, verbose_name="Usuario", unique=True)
    password = models.CharField(max_length=50, null=False, blank=False, verbose_name="Password")
    name = models.CharField(max_length=50, null=False, blank=False, verbose_name="Nombre")
    surname = models.CharField(max_length=50, null=False, blank=False, verbose_name="Apellidos")
    companyEmail = models.EmailField(max_length=50, null=False, blank=False, verbose_name="Email Compañia")
    personalEmail = models.EmailField(max_length=50, null=False, blank=False, verbose_name="Email Personal")
    active = models.BooleanField(max_length=50, null=False, blank=False, verbose_name="Activo")
    createdDate = models.DateField(max_length=50, null=False, blank=False, verbose_name="Fecha de Creacion")
    terminationDate = models.DateField(max_length=50, null=True, blank=True, verbose_name="Fecha de Finalizacion")
    imagen = models.ImageField(upload_to='images/', null=True, blank=True, verbose_name="Url Imagen")
