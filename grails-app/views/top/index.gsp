<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'top20.css')}" type="text/css">
<body>

	<div class="home">
		<g:link controller="artist" class="homePage" action="index" >Home</g:link>
	</div>
			
	<div class="title-box">
        <h2 class="title-box_primary">Top music 2015</h2>
   	</div>
   	
   	<div class="tops-content">
   		<ul class="tops">
   			
   			<li class="top">
   	            <div class="top-title-box">
   	            	<h3 class="top-title">Top <strong>artists</strong></h3>
   	            </div>
   	            
   	            <g:each in="${topArtists}" var="entry" status="i">
   	            	<ol class="top">
   	            		 <li>
   	            		 	<g:message message="${entry.artistFirstName} ${entry.artistLastName} (${entry.voteNo} votes)"/>
   	            		 </li>
   	            	</ol>
   	            </g:each>
           	</li>
           	
			<li class="top">
   	            <div class="top-title-box">
   	            	<h3 class="top-title">Top <strong>songs</strong></h3>
   	            </div>
   	            
   	            <g:each in="${topSongs}" var="entry" status="i">
   	            	<ol class="top">
   	            		 <li>
   	            		 	<g:message message="${entry.songName}"/>
   	            		 	<span><g:message message="by ${entry.artistFirstName} ${entry.artistLastName}(${entry.voteNo} votes)"/></span>
   	            		 </li>
   	            	</ol>
   	            </g:each>
           	</li>
           	
           	<g:select name="votedWeek" from="${votedWeeks}" />
		</ul>
	</div>
	
</body>
</html>