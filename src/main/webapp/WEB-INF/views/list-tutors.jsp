<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Tutor Management</title>
	</head>
	<body>
        	<h2>Tutor List</h2>
		<!-- put button: Add Tutor -->
		<input type="button" value="Add Tutor" onclick="window.location.href='addTutorForm'; return false;"/>
		<!--  add a search box -->
        <form:form action="search" method="POST">
			    <input type="text" placeholder="Search By Tutor Name/Place" name="theSearchName">
			    <input type="submit" value="Search" />
        </form:form>
			<!-- add html table here -->

		<table border="1">
			<tr class="table-success">
				<th>S No.</th>
				<th>Tutor Name
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name"> Asc </a>
				</th>
				<th>Current Status</th>
				<th>Subject
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=subject,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=subject"> Asc </a>
				</th>
				<th>Description
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description"> Asc </a>
				</th>
				<th>Duration (In Months)
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=duration,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=duration"> Asc </a>
				</th>
				<th>Price (In INR)
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=price,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=price"> Asc </a>
				</th>
				<th colspan="2">Action</th>
			</tr>
			<!-- loop over and print tutors -->
			<c:set var="index" value="${page * 5 + 1}" />
			<c:forEach var="tutor" items="${tutors }">
				<!-- construct an "update" link with tutor id -->
				<c:url var="updateLink" value="/tutor/updateTutorForm">
					<c:param name="tutorId" value="${tutor.id}"></c:param>
				</c:url>

				<!-- construct an "delete" link with tutor id -->
				<c:url var="deleteLink" value="/tutor/delete">
					<c:param name="tutorId" value="${tutor.id}"></c:param>
				</c:url>

				<tr>
					<td>${index}</td>
					<td>${tutor.name}</td>
					<c:if test="${tutor.is_available}"><td bgcolor="yellow">Available</td></c:if>
                    <c:if test="${!tutor.is_available}"><td bgcolor="red">Not Available</td></c:if>
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
					<c:if test="${tutor.is_available}"><td><a href="/updateAvailability?tutorId=${tutor.id}&status=0">Mark as unavailable</a></td></c:if>
					<c:if test="${!tutor.is_available}"><td><a href="/updateAvailability?tutorId=${tutor.id}&status=1">Mark as Available</a></td></c:if>
					<c:set var="index" value="${index + 1}" />

				</tr>
			</c:forEach>
		</table>

	        <br><br>
            	<c:choose>
                    <c:when test="${totalPage == 0}">
                        No Record Found
                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                                &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


	</body>
</html>