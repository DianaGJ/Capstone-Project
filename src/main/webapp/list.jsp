
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
<link rel="stylesheet" href="style.css" />

</head>

<body>

	<div id="top" style="margin-bottom: 10px;">
		<h1>All List</h1>
	</div>

	<div class="container center"
		style="background-color: honeydew; margin-top: 10px;">




		<table>
			<tbody>
				<c:forEach var="row" items="${passwords}">
					<c:url var="linkDelete" value="list">
						<c:param name="instruccion" value="delete"></c:param>
						<c:param name="id" value="${row.id}"></c:param>
					</c:url>
					<c:url var="linkEdit" value="list">
						<c:param name="instruccion" value="edit"></c:param>
						<c:param name="id" value="${row.id}"></c:param>
					</c:url>
					<tr>
						<td style="width: 10.00%;" scope="row"><c:out
								value="${row.website}" /></td>
						<td style="width: 10.00%;" scope="row"><c:out
								value="${row.password}" /></td>

						<td style="width: 10.00%" scope="row"><a href="${linkEdit}">edit</a></td>
						<td style="width: 10.00%" scope="row"><a href="${linkDelete}"
							onclick="return confirm('Want to delete this password?');">delete</a></td>
					</tr>

				</c:forEach>

			</tbody>

		</table>

		<br> <br>
		<div>

			<table>
				<tr>
					<td><a id="sbutton" class="button " href="details.jsp">Add</a></td>
					<td><a id="sbutton" class="button"  href="GeneratePassword">Generate Code</a></td>
					<td><a id="sbutton" class="button " href="index.jsp">Log
							Out</a></td>
				</tr>
			</table>
		</div>
		<br>


	</div>


</body>
</html>