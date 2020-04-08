<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css"
    />
    <link rel="stylesheet" href="css/style.css" />
    <title>books</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container">
        <a class="navbar-brand" href="/"><i class="fas fa-book-reader"></i> Bookstore</a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#"
                >Home <span class="sr-only">(current)</span></a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
      <div class="row">
        <div class="col-sm-6 my-2">
          <div class="card">
            <div class="card-body">
              <h5 class="formTitle card-title">Add Book</h5>
              
            
              <form  action="index" name="book"  method="post" id="bookform">
                <div class="form-group">
                  <label for="title">Title</label>
                  <input
                    id="title"
                    class="form-control"
                    type="text"
                    name="title"
                    value=""${(book.title)!''}"
                    
                  />
                </div>
                <div class="form-group">
                  <label for="author">Author</label>
                  <input
                    id="author"
                    class="form-control"
                    type="text"
                    name="author"
                    value=""${(book.author)!''}"
                    
                  />
                   <input type="hidden" id="inputbookid" name="bookid" value="ddsdsdd">
                </div>
                  <div style="display: flex; justify-content: flex-end;" >
		            <input type="reset" class="mx-4 btn btn-secondary" value="Reset" />
           		    <button class="formSubmit btn btn-primary" type="submit">Add</button>
                </div>
      			<!-- Check if errorMessage is not null and not empty --> 
		        <#if errorMessage?has_content>
		            <div class="error">${errorMessage}</div>
		        </#if>       
              </form>
              
            </div>
          </div>
        </div>
        <div id="booklist" class="col-sm-6 my-2">
				 <#if addSuccess??>
					 <div class="displayAlert alert alert-success alert-dismissible fade show">  
            			<button type="button" class="close" data-dismiss="alert">×</button>  
            			 ${addSuccess}  
        			 </div>  
				 </#if>
				 <#if deleteMsg??>
					 <div class="displayAlert alert alert-danger alert-dismissible fade show">  
            			<button type="button" class="close" data-dismiss="alert">×</button>  
            			 ${deleteMsg}  
        			 </div>  
				 </#if>
				 <#if editSuccess??>
					 <div class="displayAlert alert alert-info alert-dismissible fade show">  
            			<button type="button" class="close" data-dismiss="alert">×</button>  
            			 ${editSuccess}  
        			 </div>  
				 </#if>
		
        	<h5 class="p-2">Book Collection</h5>
        	<#if (books?? && books?size != 0)>
        	<#list books as book>
        	
	       	<div class="card card-body my-1 m-0 p-0">
	          <blockquote  class="blockquote">
	  			<div bookId=${book.bookid} style="display:flex;"><p style="flex-grow:11;" class="mb-0 px-2">
	  				<i class="text-info mx-2 fa-xs fa fa-book" ></i> ${book.title} </p>
		  			<span>
		  				  <i class="mx-2 text-success far fa-edit"></i>
		  				<a class="deleteItem" href="/books/${book.bookid}/delete"><i class="mx-2  text-danger far fa-trash-alt"></i></a>
		  			</span>
	  			</div>
	  			<footer class="mx-5 px-4 blockquote-footer"> ${book.author}</footer>
			  </blockquote>
	        </div>
	        </#list>
	        <#else>
           	 <div class="card card-body ">
	  			<div >
	  				<i  class="mx-2 text-danger fa-lg  fas fa-ban"></i> All books are removed..! </p>
	  			</div>
	        </div>
            </#if>
        </div>
      </div>


      <div class="stats bg-light card card-body p-1">
      	  <p style="display:flex; justify-content: space-around;">
	      	  <span><i class="hostname text-secondary mx-1 fa-sm fa fa-cog"></i> Host:  ${host}</span>
	      	  <span><i class="port text-secondary mx-1 fa-sm fa fa-cog"></i> Port:  ${port}</span>
	      	  <span><i class="protocol text-secondary mx-1 fa-sm fa fa-cog"></i> Protocol:  ${scheme}</span>
	      	  <span><i class="text-secondary mx-1 fa-sm fa fa-cog"></i> Book Count : ${bookCount}</span>
      	  </p>
      </div>
      
    </div>
     <footer class="bg-secondary text-light footer">
      <div class="container " style="display:flex; justify-content: space-around;">
        <p><i class="mx-1 fas fa-book-reader"></i> Bookstore App </p>
        <p><i class="mx-1 fas fa-heart"></i> Built for a community that learns together</p>
        <lead><i class="mx-1 far fa-paper-plane"></i> version : 1.0.0</lead>
      </div>
    </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
  </body>
</html>
