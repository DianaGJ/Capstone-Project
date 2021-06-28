<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: All List</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>All List</h1></div>
</head>

<body>



	<div class="container">
		<br>
		<br>
		<br> <br>
		<button id="sbutton" class="button" type="submit">Add</button>
		<c:forEach var="row" items="${users}">
			<c:url var="linkDelete" value="index">
				<c:param name="instruccion" value="delete"></c:param>
				<c:param name="id" value="${row.id}"></c:param>
			</c:url>
			<c:url var="linkEdit" value="index">
				<c:param name="instruccion" value="load"></c:param>
				<c:param name="id" value="${row.id}"></c:param>
			</c:url>

			<table class="table table-borderless ">
				<tbody class="table table-borderless ">
					<tr>
						<td style="width: 10.00%;" scope="row"><c:out
								value="${row.username}" /></td>
						<td style="width: 10.00%;" scope="row"><c:out
								value="${row.password}" /></td>

						<td style="width: 10.00%" scope="row"><a href="${linkEdit}">editar</a></td>
						<td style="width: 10.00%" scope="row"><a href="${linkDelet}">eliminar</a></td>
					</tr>

				</tbody>
			</table>

		</c:forEach>
	</div>


</body>
</html>