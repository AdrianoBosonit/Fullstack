const API_URL = 'http://localhost:8080';
const URL_PUT = 'http://localhost:8080/update/';
let idPersona;

window.onload = () => {
    document.querySelector('#formulario').addEventListener("submit", handleSubmit);
    getUsuarios();
}

const getUsuarios = () => {
    fetch(API_URL + "/get")
        .then(response => response.json())
        .then(users => {
            if (users && users.length > 0) {
                let user = users[0];
                idPersona = user.id;
                document.getElementById("inputUsuario").value = user.usuario;
                document.getElementById("inputApellido").value = user.surname;
                document.getElementById("inputUsuarioContrasena").value = "123456";
                document.getElementById("inputName").value = user.name;
                document.getElementById("inputEmailCompañia").value = user.company_email;
                document.getElementById("inputEmailPersonal").value = user.personal_email;
                document.getElementById("inputCiudad").value = user.city;
                document.getElementById("inputUrlImagen").value = user.imagen_url;
                document.getElementById("inputFechaCreacion").value = user.created_date.substring(0, 10);
                document.getElementById("inputActivo").checked = user.active;
                if (user.termination_date != null) {
                    document.getElementById("inputFechaFinalizacion").value = user.termination_date.substring(0, 10);
                }
            }
        })

}

function handleSubmit(e) {
    e.preventDefault();
    let persona = {
        usuario: document.getElementById('inputUsuario').value,
        surname: document.getElementById('inputApellido').value,
        password: document.getElementById('inputUsuarioContrasena').value,
        name: document.getElementById('inputName').value,
        company_email: document.getElementById('inputEmailCompañia').value,
        personal_email: document.getElementById('inputEmailPersonal').value,
        city: document.getElementById('inputCiudad').value,
        imagen_url: document.getElementById('inputUrlImagen').value,
        created_date: document.getElementById('inputFechaCreacion').value,
        active: document.getElementById('inputActivo').checked,
        termination_date: document.getElementById('inputFechaFinalizacion').value
    }

    fetch(URL_PUT + idPersona, {
            method: "PUT",
            body: JSON.stringify({
                id: persona.idPersona,
                usuario: persona.usuario,
                name: persona.name,
                surname: persona.surname,
                password: persona.password,
                company_email: persona.company_email,
                personal_email: persona.personal_email,
                city: persona.city,
                imagen_url: persona.imagen_url,
                created_date: persona.created_date,
                active: persona.active,
                termination_date: persona.termination_date
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(r => r.json().then(data => ({ status: r.status, body: data })))
        .then(obj => {
            if (obj.status >= 400) {
                alert("Error " + obj.body.httpCode + "\n" + obj.body.mensaje);
            } else {
                alert("Persona actualizada correctamente");
            }
            console.log(obj)

        });

}