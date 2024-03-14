$(document).ready(function () {

    $(window).scroll(function () {
        // 현재 스크롤바의 위치값을 저장하는 변수 sct를 만듬.
        var sct = $(window).scrollTop();
        /*
        스크롤 위치에 맞춰 헤더모양 바꾸기
        만약 현재 스크롤 위치값이 87px이상이면 #header에 fix클래스 추가하기
        */

        if (sct >= 60) {
            $("#header").addClass("fix");
        } else {
            $("#header").removeClass("fix");
        }
    });

    function includeHTML() {
        let z, elmnt, file, xhttp;

        z = document.getElementsByTagName("*");

        for (let i = 0; i < z.length; i++) {
            elmnt = z[i];
            file = elmnt.getAttribute("data-include");

            if (file) {
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4) {
                        if (this.status == 200) { elmnt.innerHTML = this.responseText; }
                        if (this.status == 404) { elmnt.innerHTML = "Page not found."; }
                        /* Remove the attribute, and call this function once more: */
                        elmnt.removeAttribute("data-include");
                        includeHTML();
                    }//if
                }//onreadystatechange

                xhttp.open("GET", file, true);
                xhttp.send();
                return;
            }//if - file
        }//for
    }//includeHTML


    /* ✨ 실행 */
    window.addEventListener('DOMContentLoaded', () => {
        includeHTML();
    });



});