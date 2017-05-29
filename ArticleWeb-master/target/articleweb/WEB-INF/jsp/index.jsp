<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<!-- <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT"> -->

<title>News | Home</title>

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">

<!--[if lt IE 9]>
		<script src="static/js/html5shiv.min.js"></script>
		<script src="static/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">HOME</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="new-news">Add News</a></li>
					<li><a href="all-news">All News</a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Welcome</h1>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEWS'}">
			<div class="container text-center" id="tasksDiv">
				<h3>ALL NEWS</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Id</th>
								<th>Title</th>
								<th>Content</th>
								<th>Link</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="row" items="${newslist}">
								<tr>
									<td>${row.id}</td>
									<td>${row.title}</td>
									<td>${row.content}</td>
									<td><a href="${row.link}">link here</a></td>
									<td><a href="update-news?id=${row.id}"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a href="del-news?id=${row.id}"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="view-news?id=${row.id}"><span
											class="glyphicon glyphicon-file"></span></a></td>		
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
			<div class="container text-center">
				<h3>Manage news</h3>
				<hr>
				<form class="form-horizontal" method="GET" action="save-news">
					<div class="form-group">
						<label class="control-label col-md-3">id</label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="id"
								value="${singlenews.id}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Title</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="title"
								value="${singlenews.title}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Content</label>
						<div class="col-md-7">
							<textarea id="content" name="content" class="form-control custom-control"
								rows="3" style="resize: none">${singlenews.content}</textarea>
						</div>
					</div>	
					<div class="form-group">
						<label class="control-label col-md-3">Link upload</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="link"
												value="${singlenews.link}" />
						</div>
					</div>
					<hr>
					<div><h3>Upload to drive</h3></div>
					<div class="form-group">
						<label class="control-label col-md-3">File name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="filename"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3">File path</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="filepath"/>
						</div>
					</div>

					<div class="form-group">
						<button name="sub" type="submit" class="btn btn-danger"
							value="Save">Save</button>
						<!-- <input name="sub" type="button" class="btn btn-primary" value="Save" /> -->
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_VIEW'}">
			<div class="container ">
				<h3>${singlenews.title}</h3>
				<hr></hr>
				<h5>${singlenews.content}</h5>
				<button onclick=${singlenews.link} >Get file</button>
			</div>
		</c:when>
	</c:choose>

	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<!-- ckeditor -->
	<script src="static/ckeditor/ckeditor.js"></script>
	<script>
    	CKEDITOR.replace( 'content' );
	</script>
</body>
</html>