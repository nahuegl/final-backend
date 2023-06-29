window.addEventListener('load', function () {
    const form = document.querySelector('#update_dentist_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#dentist_id').value,
            license: document.querySelector('#license').value,
            name: document.querySelector('#name').value,
            lastName: document.querySelector('#lastName').value
            };
        const url = '/findAllDentist';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            window.location.replace("/get_dentist.html");
        })
    })
 });

function findBy(id) {
    const url = '/findDentist/byId?id='+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        console.log(odontologo);
        document.querySelector('#dentist_id').value = id;
        document.querySelector('#license').value = odontologo.license;
        document.querySelector('#name').value = odontologo.name;
        document.querySelector('#lastName').value = odontologo.lastName;
        document.querySelector('#div_dentist_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}