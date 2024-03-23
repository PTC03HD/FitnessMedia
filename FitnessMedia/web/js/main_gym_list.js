(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();


    // Initiate the wowjs
    new WOW().init();


    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";

    $(window).on("load resize", function () {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
                    function () {
                        const $this = $(this);
                        $this.addClass(showClass);
                        $this.find($dropdownToggle).attr("aria-expanded", "true");
                        $this.find($dropdownMenu).addClass(showClass);
                    },
                    function () {
                        const $this = $(this);
                        $this.removeClass(showClass);
                        $this.find($dropdownToggle).attr("aria-expanded", "false");
                        $this.find($dropdownMenu).removeClass(showClass);
                    }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        margin: 25,
        dots: false,
        loop: true,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsive: {
            0: {
                items: 1
            },
            768: {
                items: 2
            }
        }
    });

})(jQuery);

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
//
//function handleDropdownLinkClick(element) {
//    // Xóa class "active" từ tất cả các liên kết trong dropdown menu
//    var dropdownLinks = document.querySelectorAll('.dropdown-menu a');
//    dropdownLinks.forEach(function (link) {
//        link.classList.remove('active');
//    });
//
//    // Thêm class "active" vào liên kết được nhấn
//    element.classList.add('active');
//
//    // Thêm tham số vào URL khi liên kết được nhấn
//    var parameterName = element.getAttribute('data-parameter-name');
//    var parameterValue = element.getAttribute('data-parameter-value');
//    if (parameterName && parameterValue) {
//        addParametersToUrl([[parameterName, parameterValue]]);
//    }
//};

