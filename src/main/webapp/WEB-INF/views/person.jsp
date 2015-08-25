<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

<head>
<title>Person Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
<script>
	var app = angular.module('myApp', []);
	app.controller('personCtrl', function($scope) {
		$scope.name = "";
		$scope.fullName = function() {
			return $scope.name;
		}
	});
</script>
<script>
	function validateForm() {
		var x = document.forms["myForm"]["name"].value;
		var y = document.forms["myForm"]["email"].value;

		if (x == null || x == "") {
			alert("Name must be filled out");
			return false;
		}
		if (y == null || y == "") {
			alert("Email must be filled out");
			return false;
		}

	}
</script>

</head>
<body>
	<div class="container">
		<br>
		<div align="center" class="well">
			<h2>Create New User</h2>
		</div>
		<div class="container" align="center">
			<h2>User Informations</h2>
			<div ng-app="myApp" ng-controller="personCtrl">
				<c:url var="addAction" value="/person/add"></c:url>

				<form:form name="myForm" action="${addAction}" commandName="person"
					onsubmit="return validateForm()">
					<table style="border-left-color: fuchsia;">

						<c:if test="${!empty person.name}">
							<tr>
								<td><form:label path="id">
										<spring:message text="ID" />
									</form:label></td>
								<td><form:input path="id" readonly="true" size="8"
										disabled="true" /> <form:hidden path="id" /></td>
							</tr>
						</c:if>
						<tr>
							<td>
								<div class="form-group">
									<form:label path="name">
										<spring:message text="Name" />
									</form:label>
								</div>
							</td>
							<td><form:input path="name" ng-model="name" /></td>
						</tr>
						<tr>
							<td>
								<div class="form-group">
									<form:label path="email">
										<spring:message text="E-mail" />
									</form:label>
								</div>
							</td>
							<td><form:input path="email" ng-model="email" /></td>
						</tr>

						<tr>
							<td colspan="2"><c:if test="${!empty person.name}">
									<%-- 	<input type="submit"
										value="<spring:message text="Edit Person"/>" /> --%>

									<button type="submit" class="btn btn-primary"
										value="<spring:message text="Edit Person"/>">Edit
										Person</button>
								</c:if> <br> <c:if test="${empty person.name}">
									<!--  <input type="submit" value="<spring:message text="Add Person"/>" /> -->
									<button type="submit" class="btn btn-primary"
										value="<spring:message text="Add Person"/>">Add
										Person</button>
								</c:if></td>
						</tr>
					</table>
				</form:form>
				<br> <br>
				<div class="container" align="left">
					<p class="bg-info">You Enter : {{fullName()}}</p>
				</div>
			</div>

			<h3>Persons List</h3>
			<button type="button" class="btn btn-success">
				Person Count Updated <span class="badge">${countPerson}</span>
			</button>
			<br>
			<c:if test="${!empty listPersons}">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th width="40">ID</th>
							<th width="40">Name</th>
							<th width="40">E-mail</th>
							<th width="40">Edit</th>
							<th width="40">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listPersons}" var="person">
							<tr>
								<td>${person.id}</td>
								<td>${person.name}</td>
								<td>${person.email}</td>
								<td><a href="<c:url value='/edit/${person.id}' />"><button
											type="button" class="btn btn-warning">Edit</button></a></td>
								<td><a href="<c:url value='/remove/${person.id}' />"><button
											type="button" class="btn btn-danger">Delete</button></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>
