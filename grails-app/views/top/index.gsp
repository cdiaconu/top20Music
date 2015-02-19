<!DOCTYPE html>
<html>
 <head>
      <g:javascript library="jquery" plugin="jquery"/>
	  <jqui:resources components="datepicker" mode="normal" />
</head>
   
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
   	            		 	<g:message message="${i+1}."/>
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
   	            			<g:message message="${i+1}."/>
   	            		 	<g:message message="${entry.songName}"/>
   	            		 	<span><g:message message="by ${entry.artistFirstName} ${entry.artistLastName}(${entry.voteNo} votes)"/></span>
   	            		 </li>
   	            	</ol>
   	            </g:each>
           	</li>
           	
		</ul>
	</div>
	
	<div class="top-period-content">
		<g:form controller="top">
			<div class="top-title-box">
   				<h3 class="top-title">Top <strong>songs</strong> for period</h3>
   			</div>
   			<div class="datePicker">
				<g:message message="From date: "/>
	   			<g:datePicker id="fromDate" name="fromDate" value="${fromDate}" precision="day" />
	   		</div>
	   		<div class="datePicker">
	   			<span><g:message class="msg" message="To date: "/></span>
	   			<span class="dateToDate"><g:datePicker id="toDate" name="toDate" value="${toDate}" precision="day" /></span>
	   		</div>
	        <g:actionSubmit class="show-top" value="Show Top" action="index"/>
		</g:form>
		<ul>
			<li class="top-period">
   	            <g:each in="${topSongsForPeriod}" var="entry" status="i">
   	            	<ol class="top">
   	            		 <li>
   	            		 	<g:message message="${i+1}."/>
   	            		 	<g:message message="${entry.songName}"/>
   	            		 	<span><g:message message="by ${entry.artistFirstName} ${entry.artistLastName}(${entry.voteNo} votes)"/></span>
   	            		 </li>
   	            	</ol>
   	            </g:each>
           	</li>
		</ul>
	</div>
	
</body>
</html>