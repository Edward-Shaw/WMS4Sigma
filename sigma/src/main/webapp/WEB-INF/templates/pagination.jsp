<%@page import="java.util.List"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="org.apache.http.NameValuePair"%>
<%@page import="org.apache.http.client.utils.URLEncodedUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
	List<NameValuePair> params = URLEncodedUtils.parse(request.getQueryString(), Charset.forName("utf-8"));
	request.setAttribute("params", params);
%>

<script>
	var target = new Object();
	<c:forEach items="${params}" var="i">
		target['${i.name}'] = '${i.value}';
	</c:forEach>

	var goto = function(page){
		target.page = page;
		var queryString = "";
		for(attr in target){
			queryString += '&' + attr + '=' + target[attr];
		}
		window.location.href = "${requestScope['javax.servlet.forward.request_uri']}?" + queryString.substring(1);
	};
</script>
<!-- 计算分页数 -->
<c:set var="page" value="${empty param.page ? 0 : param.page }"></c:set>
<c:set var="size" value="${empty param.size ? 50 : param.size }"></c:set>
<c:set var="amount" value="${paging.count }"></c:set>
<c:set var="totalPage" value="${amount % size == 0 ? 	amount/size : (amount-amount%size)/size+1}"></c:set>

<div class="row-fluid">
	<tiles:insertAttribute name="listing"/>
</div>

<div class="row-fluid" align='center'>
	<ul class="pagination pagination-sm">
			<li class="${page < 1 ? 'disabled' : ''}">
				<a href="javascript:${page >= 1 ? 'goto(' : 'void(0'}${page >= 1 ? page - 1 : '' });">&laquo;</a>
			</li>
		
		<!-- 设置分页长度 -->
		<c:set var="pagination_length" value="10"></c:set>

		<c:choose>
			<%-- 总页数小于  pagination_length 时， 不隐藏任何页码--%>
			<%-- 1 2 3 4 ...pagination_length --%>
			<c:when test="${totalPage <= pagination_length }">
				<c:set var="beginPage" value="0"></c:set>
				<c:set var="endPage" value="${totalPage-1}"></c:set>
			</c:when>
			<c:otherwise>
				<%-- 假设pagination_length为10，则当前页为前5页时，第一页显示1--%>
				<%-- 1 2 3 .. 当前页(page) ... pagination_length--%>
				<c:if test="${page <= pagination_length / 2 }">
					<c:set var="beginPage" value="0"></c:set>
					<c:set var="endPage" value="${pagination_length }"></c:set>
				</c:if>

				<%-- 假设pagination_length为10，则当前页为后5页时，最后一页显示totalPage--%>
				<%-- n n+1 n+2 .. 当前页(page) ... pagination_length--%>
				<c:if test="${totalPage - page <= pagination_length / 2 }">
				    <c:set var="beginPage" value="${totalPage - pagination_length + 1 }"></c:set>
					<c:set var="endPage" value="${totalPage}"></c:set>
				</c:if>

				<%-- n n+1 n+2 .. 当前页(page) ... n+17 n+18 n+19--%>
				<c:if
					test="${page > pagination_length / 2 && (totalPage - page > pagination_length / 2) }">
					<c:set var="beginPage" value="${page - pagination_length / 2}"></c:set>
					<c:set var="endPage" value="${page + pagination_length / 2}"></c:set>
				</c:if>

			</c:otherwise>
		</c:choose>

		<c:forEach begin="${beginPage < 0 ? 0 :  beginPage}" end="${endPage < 0 ? 0 : endPage }" varStatus="pageObject">
			<c:if test="${page == pageObject.index }">
				<li class="active"><a href="javascript:void(0)">
						${pageObject.index }
				</a></li>
			</c:if>
			<c:if test="${page != pageObject.index }">
				<li><a href="javascript:goto(${pageObject.index});">${pageObject.index } </a></li>
			</c:if>
		</c:forEach>

		<li class="${page >= totalPage-1 ? 'disabled' : ''}">
				<a href="javascript:${page < totalPage-1 ? 'goto(' : 'void(0'}${page < totalPage ? page + 1 : '' });">&raquo;</a>
		</li>

		<li class="disabled"><span>共${totalPage}页</span>
	</ul>
</div>