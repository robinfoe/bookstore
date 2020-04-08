



window.setTimeout(function() {
        // This will execute 5 seconds later
        var alert = document.querySelector('.displayAlert');
        if (alert != null) {
        	alert.style.display = 'none';
        }
    }, 2000);

var books = document.querySelector("#booklist");

books.addEventListener("click", e => {
	  if (e.target.classList.contains("fa-edit")) {
	    let item = e.target.parentElement.parentElement;
	    var title = item.querySelector('p').textContent;
	    var author = item.parentElement.querySelector('footer').textContent;
	    var bookid = item.parentElement.querySelector('div').getAttribute("bookid");

	    item.parentElement.parentElement.setAttribute("class", "editing card card-body my-1 m-0 p-0");
	    
	    var bookForm = document.querySelector("#bookform");
	    bookForm.setAttribute("action", "/books/"+ bookid);
	    bookForm.querySelector("#title").value = title.trim();
	    bookForm.querySelector("#author").value = author.trim();
	    bookForm.querySelector("#inputbookid").value = bookid.trim();
	    document.querySelector(".formTitle").innerHTML = "Edit Book";
	    document.querySelector('button[type="submit"]').innerHTML = "Edit";
	    document.querySelector('button[type="submit"]').classList.remove("btn-primary");
	    document.querySelector('button[type="submit"]').classList.add("btn-info");
	    
	  }
	});
