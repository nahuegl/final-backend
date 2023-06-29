
window.addEventListener('load', function () {
    const form = document.querySelector('#add_new_dentist');
    form.addEventListener('submit', (e) => {
    console.log(e)
        e.preventDefault();
        const formData = {
            license: document.querySelector('#matricula').value,
            name: document.querySelector('#nombre').value,
            lastName: document.querySelector('#apellido').value,
        };
        console.log(formData)
        const url = '/saveDentist';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<div> <p>Se agregó correctamente el odontólogo</p> </div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                '<div> <p>No se pudo guardar el odontólogo. </p></div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
    });
    const resetUploadForm = () => {
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }
});