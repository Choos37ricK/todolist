$(function() {

$('#save-task').click(function() {
    var data = $('#task-form form').serialize();

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