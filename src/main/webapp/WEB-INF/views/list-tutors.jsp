<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Tutor Management</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded"><a href="${pageContext.request.contextPath}/tutor/list" class="text-white headerLink">Tutor List</a></h2>

		<!-- put button: Add Tutor -->
		<input type="button" class="btn btn-outline-info" value="Add Tutor" onclick="window.location.href='addTutorForm'; return false;"/>
		<!--  add a search box -->
        <form:form action="search" method="POST" cssClass="form-inline mt-3 mb-4">
			<div class="input-group mb-2 mr-sm-2">
			    <div class="input-group-prepend">
			      <div class="input-group-text"><i class="fa fa-search" aria-hidden="true"></i></div>
			    </div>
			    <input type="text" class="form-control" placeholder="Search By Tutor Name/Place" name="theSearchName">
			    <input type="submit" value="Search" class="btn btn-info ml-2" />
			</div>
        </form:form>
			<!-- add html table here -->
		<table class="table text-center table-striped mt-3 table-bordered" border="1">
			<tr class="table-success">
				<th>#</th>
				<th>Tutor Name</th>
				<th>Subject</th>
				<th>Description</th>
				<th>Duration (In Months)</th>
				<th>Price (In INR)</th>
				<th colspan="2">Action</th>
			</tr>
			<!-- loop over and print tutors -->
			<c:set var="index" value="0" />
			<c:forEach var="tutor" items="${tutors }">
				<!-- construct an "update" link with tutor id -->
				<c:url var="updateLink" value="/tutor/updateTutorForm">
					<c:param name="tutorId" value="${tutor.id}"></c:param>
				</c:url>

				<!-- construct an "delete" link with tutor id -->
				<c:url var="deleteLink" value="/tutor/delete">
					<c:param name="tutorId" value="${tutor.id}"></c:param>
				</c:url>

				<c:set var="index" value="${index + 1}" />
				<tr>
					<td>${index}</td>
					<td>${tutor.name}</td>
					<td>${tutor.subject}</td>
					<td>${tutor.description}</td>
					<td>${tutor.duration}</td>
					<td>${tutor.price}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" class="btn btn-danger btn-sm" onclick="if (!(confirm('Are you sure you want to delete this tutor?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</body>
</html>