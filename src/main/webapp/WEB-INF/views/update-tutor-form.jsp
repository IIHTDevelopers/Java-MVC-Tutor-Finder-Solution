<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Tutor Management</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded">Tutors </h2>
		<h3 class="text-center">Update Tutor</h3>
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<hr>
				<form:form action="saveTutor" modelAttribute="tutor" method="POST">
					<!-- need to associate this data with tutor id -->
					<form:hidden path="id"/>
					<div class="form-group">
						<label for="name">Tutor Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="name" name="name" class="form-control"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
					<div class="form-group">
						<label for="subject">Subject <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="subject" name="subject" class="form-control"/>
						<form:errors path="subject" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<label for="description">Course Description <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:textarea path="description" name="description" class="form-control"/>
						<form:errors path="description" cssClass="text-danger"/>
					</div>
                    <div class="form-group">
						<label for="duration">Course Duration <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input type="number" path="duration" name="duration" class="form-control" placeholder = "In Months"/>
						<form:errors path="duration" cssClass="text-danger"/>
					</div>
                    <div class="form-group">
						<label for="price">Course Price <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input type="number" path="price" name="price" class="form-control" step="0.01"/>
						<form:errors path="price" cssClass="text-danger"/>
					</div>
					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/tutor/list" class="badge badge-secondary">Back to List</a>
	</div>
	</body>
</html>