$(document).ready(function () {
    document.addEventListener("DOMContentLoaded", function () {

        // 현재 페이지의 URL에서 'id'를 가져옵니다. (실제 데이터로 대체되어야 함)
        var id = "manu"; // 실제 데이터로 대체되어야 합니다.

        // 메뉴 선택을 로컬 스토리지에서 가져옵니다.
        var selectedMenu = localStorage.getItem('selectedMenu');

        // 모든 nav-link 클래스를 가진 요소들을 가져옵니다.
        var navLinks = document.querySelectorAll('.nav-link');

        // 함수를 정의하여 중복되는 부분을 처리합니다.
        function setActive(link) {
            // 현재 클릭된 링크에 active 클래스를 추가합니다.
            link.classList.add('active');

            // 로컬 스토리지에 선택된 메뉴를 저장합니다.
            localStorage.setItem('selectedMenu', link.getAttribute('href'));

            // 모든 링크에서 active 클래스를 제거합니다.
            navLinks.forEach(function (navLink) {
                if (navLink !== link) {
                    navLink.classList.remove('active');
                }
            });
        }

        // 각 링크를 순회하며 클릭 이벤트를 추가합니다.
        navLinks.forEach(function (link) {
            link.addEventListener('click', function () {
                setActive(link);
            });

            // 만약 현재 링크의 href 속성 값이 로컬 스토리지에 저장된 선택된 메뉴와 일치한다면 active 클래스를 추가합니다.
            if (link.getAttribute('href') === selectedMenu || (selectedMenu === null && link.getAttribute('comphome') === id)) {
                setActive(link);
            }
        });
    });

});