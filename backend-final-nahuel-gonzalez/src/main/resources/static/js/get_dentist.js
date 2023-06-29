window.addEventListener('load', function () {
    (function(){
      const url = '/findAllDentist';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(odontologo of data) {
            let table = document.getElementById("dentistTable");
            let odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                      ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                      "✏" +
                                      '</button>';

            odontologoRow.innerHTML =
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.license + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.name + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.lastName + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })
    })
    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_dentist.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
    })