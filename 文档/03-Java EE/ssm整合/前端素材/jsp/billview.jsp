<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>订单管理页面 >> 信息查看</span>
     </div>
     <div class="providerView">
         <p><strong>订单编号：</strong><span>${bill.billCode }</span></p>
         <p><strong>商品名称：</strong><span>${bill.productName }</span></p>
         <p><strong>商品单位：</strong><span>${bill.productUnit }</span></p>
         <p><strong>商品数量：</strong><span>${bill.productCount }</span></p>
         <p><strong>总金额：</strong><span>${bill.totalPrice }</span></p>
         <p><strong>供应商：</strong><span>${bill.providerName }</span></p>
         <p><strong>是否付款：</strong>
         	<span>
         		<c:if test="${bill.isPayment == 1}">未付款</c:if>
				<c:if test="${bill.isPayment == 2}">已付款</c:if>
			</span>
		</p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billview.js"></script>