/**
 * 
 */


//image slide

var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
  showDivs2(slideIndex2 += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("myImageSlides");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}


var myIndex = 0;
carousel1();

function carousel1() {
  var i;
  var x = document.getElementsByClassName("myImageSlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none"; 
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel1, 4000); // Change image every 2 seconds
}


// text slide
var slideIndex3 = 0;
carousel2();

function carousel2() {
  var i;
  var x = document.getElementsByClassName("myTextSlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none"; 
  }
  slideIndex3++;
  if (slideIndex3 > x.length) {slideIndex3 = 1} 
  x[slideIndex3-1].style.display = "block"; 
  setTimeout(carousel2, 4000); 
}

var slideIndex2 = 1;
showDivs2(slideIndex2);

function showDivs2(n) {
  var i;
  var x = document.getElementsByClassName("myTextSlides");
  if (n > x.length) {slideIndex2 = 1}
  if (n < 1) {slideIndex2 = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex2-1].style.display = "block";  
}

