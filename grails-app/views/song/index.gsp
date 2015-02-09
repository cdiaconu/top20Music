<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'top20.css')}" type="text/css">
<body>
		
	<div class="title-box">
        <h2 class="title-box_primary">List of songs by <strong>${artist.firstName} ${artist.lastName}</strong></h2>
   	</div>
   	
   	<div class="content">
   		<div class="add-new">
	   		<g:form controller="artist">
	   			<g:hiddenField name="id" value="${artist?.id}" />
	           	<g:textField class="artist-input" name="firstName" value="${artist ? artist.firstName :''}"/>
	           	<g:textField class="artist-input" name="lastName"  value="${artist.lastName}"/>
	           	<g:actionSubmit class="artist-add" value="Update" action="update"/>
	      	 </g:form>
	   	</div>
	   	
	   	<g:if test="${flash.message}">
			<div class="flashMessage" role="status">${flash.message}</div>
		</g:if>
		
		<ul class="artist-list">
       		<g:each in="${songs}" var="song" status="i">
				<li class="artist">
    	            <div class="artist-name">
    	            	<g:message message="${song.name}"/>
    	            </div>
        	        <div class="artist-delete">
            	        <g:link action="delete" id="${artist.id}">
            	        	<img style="height:35px; width:35px" title="Delete Song" src="${resource(dir:'images',  file:'icon_delete.png')}"/>
            	        </g:link>
                	</div>
            	</li>
	       </g:each>
		</ul>
		
   	</div>
   	
</body>
</html>