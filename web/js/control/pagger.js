function renderPagger(id, pageindex, gap, totalpage, baseUrl) {
    var container = document.getElementById(id);
    var content = '';

    // Link về trang đầu
    if (pageindex > 1) {
        content += '<a href="' + baseUrl + '?page=1">First</a>';
    }

    // Tính toán start - end
    var start = Math.max(1, pageindex - gap);
    var end = Math.min(totalpage, pageindex + gap);

    for (var i = start; i <= end; i++) {
        if (i === pageindex) {
            content += '<span class="current">' + i + '</span>';
        } else {
            content += '<a href="' + baseUrl + '?page=' + i + '">' + i + '</a>';
        }
    }

    // Link về trang cuối
    if (pageindex < totalpage) {
        content += '<a href="' + baseUrl + '?page=' + totalpage + '">Last</a>';
    }

    container.innerHTML = content;
}
