document.addEventListener('DOMContentLoaded', function () {
    var showMoreBtn = document.getElementById('show-more-btn');
    var showLessBtn = document.getElementById('show-less-btn');
    var comments = document.querySelectorAll('.comments-item');
    var visibleItemCount = 2; // Số lượng phần tử đầu tiên hiển thị

    showMoreBtn.addEventListener('click', function () {
        visibleItemCount += 2; // Tăng số lượng phần tử hiển thị lên 2
        for (var i = 0; i < comments.length; i++) {
            comments[i].style.display = (i < visibleItemCount) ? 'block' : 'none';
        }
        if (visibleItemCount >= comments.length) {
            showMoreBtn.style.display = 'none'; // Ẩn nút "Show More" khi đã hiển thị hết tất cả phần tử
            showLessBtn.style.display = 'block'; // Hiển thị nút "Show Less" khi đã hiển thị hết tất cả phần tử
        }
    });

    showLessBtn.addEventListener('click', function () {
        visibleItemCount = 2; // Đặt lại số lượng phần tử hiển thị về 2
        for (var i = 0; i < comments.length; i++) {
            comments[i].style.display = (i < visibleItemCount) ? 'block' : 'none';
        }
        showMoreBtn.style.display = 'block'; // Hiển thị nút "Show More" khi click vào nút "Show Less"
        showLessBtn.style.display = 'none'; // Ẩn nút "Show Less" khi click vào nút "Show Less"
    });

});

function addParametersToUrl(parameters) {
    console.log("aloalo");
    var url = window.location.href;
    var urlParams = new URLSearchParams(window.location.search);

    // Duyệt qua mỗi cặp tham số và giá trị
    parameters.forEach(function (param) {
        var paramName = param[0];
        var paramValue = param[1];

        // Kiểm tra xem tham số đã tồn tại trong URL hay chưa
        if (urlParams.has(paramName)) {
            // Nếu đã tồn tại, cập nhật giá trị của tham số
            urlParams.set(paramName, paramValue);
        } else {
            // Nếu không tồn tại, thêm mới tham số và giá trị vào URL
            urlParams.append(paramName, paramValue);
        }
    });

    // Cập nhật URL với các tham số mới hoặc cập nhật
    var newUrl = url.split('?')[0] + '?' + urlParams.toString();

    // Chuyển hướng đến URL mới
    window.location.href = newUrl;

}

// L?y ph?n t? textarea trong form

                            