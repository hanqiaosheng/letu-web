<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="row">
	<div class="col-sm-6">
		<div class="dataTables_info" id="dataTables-example_info"
			role="status" aria-live="polite">总共${totalPage}页 ，
			当前第${pageIndex}/${totalPage}页</div>
	</div>
	<div class="col-sm-6">
		<div class="dataTables_paginate paging_simple_numbers"
			id="dataTables-example_paginate">
			<div class="pull-right page-box" id="pagediv1">
				<c:if test="${pageIndex>1}">
					<span class="paginate_button active" onclick="pageChanged('prev')">前一页</span>
				</c:if>
				<c:if test="${pageIndex<totalPage}">
					<span onclick="pageChanged('next')">后一页</span>
				</c:if>
				<c:if test="${totalPage>1}">
					<input type="number" style="width: 50px" id="pageNum"
						value="${pageIndex }"> <span id="jump" onclick="return jump()">GO</span>
				</c:if>
			</div>
		</div>
	</div>
</div>