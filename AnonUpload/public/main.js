function getFiles(data){
    for (var i in data){
        var elem = $("<a>");
        elem.attr("href", "files/" + data[i].filename);
        elem.text(data[i].origFilename);
        $("#files").append($("<br>"));
        $("#files").append(elem);
    }
}

$.get("/files", getFiles);