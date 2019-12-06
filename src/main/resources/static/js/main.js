$(function() {

function isValid(str) {
    var reg = new RegExp("(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)");
    return reg.test(str);
}

$('#save-task').click(function() {
    var data = $('#task-form form').serialize();

    var date = document.forms[0].elements[1].value;
    if (!isValid(date)) {
        alert('Введите дату в верном формате!');
        return false;
    }



    $.ajax({
        method: "POST",
        url: '/events/',
        data: data,
        success: function(response) {
            //$('#task-form').css('display', 'none');

            var task = {};

            task.id = response;

            var dataArray = $('#task-form form').serializeArray();



            for(i in dataArray) {
                task[dataArray[i]['name']] = dataArray[i]['value'];
            }

            appendTask(task);
        }
    });
    return false;
});

const appendTask = function(data) {
    var taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name +'</a><br>';
    $('#task-list').append('<div>' + taskCode + '</div>');
};

});