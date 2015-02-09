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
		
   	</div>
   	
</body>
</html>