<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath() %>/static/pages/js/videosList.js?v=1.1" type="text/javascript"></script>

	<!-- BEGIN PAGE HEADER-->
	<!-- BEGIN PAGE BAR -->
	<div class="page-bar">
	    <ul class="page-breadcrumb">
	        <li>
	            <span>首页</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>短视频信息</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>短视频列表</span>
	        </li>
	    </ul>
	</div>
	<!-- END PAGE BAR -->
	<!-- END PAGE HEADER-->
        
    <!-- 视频信息列表 jqgrid start -->                
	<div class="row">
	
		
	
	
    	<div class="col-md-12">
			<br/>
			
			<div class="videosList_wrapper">  
			    <table id="videosList"></table>  
			    <div id="videosListPager"></div>  
			</div>  
			
		</div>
	</div>
	<!-- 用户信息列表 jqgrid end -->
	
