<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'top20.css')}" type="text/css">
<body>

	<div class="home">
		<g:link class="homePage" action="index" >Home</g:link>
		<g:link controller="top" class="homePage" action="index" >Top music</g:link>
	</div>
			
	<div class="title-box">
        <h2 class="title-box_primary">List of <strong>artists</strong></h2>
   	</div>
   	
   	<div class="content">
	   	<div class="add-new">
	   		<g:form controller="artist">
	   			<span class="required-indicator">*</span>
	           	<g:textField class="artist-input ${hasErrors(bean:artistErr,field:'firstName','errors')}" placeholder="First Name" name="firstName"/>
	           	<g:textField class="artist-input" placeholder="Last Name" name="lastName"/>
	           	<g:actionSubmit class="artist-add" value="Add New" action="save"/>
	      	 </g:form>
	   	</div>
	   	
	   	<div class="errMessage"><g:renderErrors bean="${artistErr}" /></div>
		
	   	<g:if test="${flash.message}">
			<div class="flashMessage" role="status">${flash.message}</div>
		</g:if>
		
		<ul class="artist-list">
       		<g:each in="${artists}" var="artist" status="i">
	            <li class="artist">
    	            <div class="artist-name">
    	            	<g:link controller="song" action="index" id="${artist.id}">${artist.firstName} ${artist.lastName} (${artist.songs.size()} songs)</g:link>
    	            </div>
    	            
        	        <div class="artist-delete">
            	        <g:link action="delete" id="${artist.id}">
            	        	<img style="height:35px; width:35px" title="Delete Artist" src="${resource(dir:'images',  file:'icon_delete.png')}"/>
            	        </g:link>
                	</div>
            	</li>
	       </g:each>
		</ul>
       
	</div>
</body>
</html>