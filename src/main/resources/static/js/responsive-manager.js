function navResponsive() {
    var nav = document.getElementById("nav");

    if (nav.className === "topnav") {
        nav.className += " responsive";
    } else {
        nav.className = "topnav";
    }
}

function hideNav() {
    var nav = document.getElementById("nav");

    if(nav.className === "topnav responsive") {
        nav.className = "topnav";
    }
}