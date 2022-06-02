<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<body>

<div class="container">
  <div class="row py-2">

    <div class="col-12">
      <table class="table table-bordered my-2">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Title</th>
          <th scope="col">Cost</th>
        </tr>
        </thead>
        <tbody>
        <forEach var="product" items="${requestScope.products}">
          <tr>
            <th scope="row">
              <c:out value="${product.id}"/>
            </th>
            <td>
              <c:out value="${product.title}"/>
            </td>
            <td>
              $ <c:out value="${product.cost}"/>
            </td>

          </tr>
        </forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

</body>
</html>