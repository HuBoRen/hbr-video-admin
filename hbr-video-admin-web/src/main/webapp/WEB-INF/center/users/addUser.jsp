<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- BEGIN PAGE HEADER-->
	<!-- BEGIN PAGE BAR -->
	<div class="page-bar">
	    <ul class="page-breadcrumb">
	        <li>
	            <span>首页</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>用户信息</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>添加用户</span>
	        </li>
	    </ul>
	</div>
	<!-- END PAGE BAR -->
	<!-- END PAGE HEADER-->
                        
	<div class="row">
    	<div class="col-md-12">
			<br/>
			<!-- 意见反馈 start -->
			<div class="tabbable-line boxless tabbable-reversed">
            	<div class="portlet box green-jungle">
                	<div class="portlet-title">
                    	<div class="caption">
                    		<i class="icon-plus"></i>添加用户
                    	</div>
					</div>
					
					<div class="portlet-body form">
					
	                    <!-- BEGIN FORM-->
	                    <form id="addUserForm" class="form-horizontal">
		                    <div class="form-body">
		                    
		                    	<div class="form-group">
		                        	<label class="col-md-3 control-label"><span class="field-required"> * </span>用户名：</label>
		                            <div class="col-md-4">
		                            	<div id="input-error">
		                            		<input id="username" name="username" type="text" class="form-control" placeholder="用户名不能超过8个字，请注意" 
		                            		onblur=checkUserName()>
		                            	</div>
									</div>
								</div>
								
								<div class="form-group">
		                        	<label class="col-md-3 control-label"><span class="field-required"> * </span>昵称：</label>
		                            <div class="col-md-4">
		                            	<div id="input-error">
		                            		<input id="nickname" name="nickname" type="text" class="form-control" placeholder="昵称不能超过8个字，请注意">
		                            	</div>
									</div>
								</div>
								<div class="form-group">
		                        	<label class="col-md-3 control-label"><span class="field-required"> * </span>初始密码：</label>
		                            <div class="col-md-4">
		                            	<div id="input-error">
		                            		<input id="password" name="password" type="text" class="form-control" placeholder="用户名不能超过15个字，请注意">
		                            	</div>
									</div>
								</div>
								
								
		                                                  
							</div>
	                                                        
							<div class="form-actions">
			                    <div class="row">
			                        <div class="col-md-offset-3 col-md-9">
			                            <button type="submit" class="btn green-jungle">提 交</button>
			                            <button type="reset" class="btn grey-salsa btn-outline">取  消</button>
			                        </div>
			                    </div>
							</div>
						</form>
						<!-- END FORM-->
						
					</div>
				</div>
			</div>
                            
		</div>
	</div>
	
<script type="text/javascript">
    
var checkUserName=function(){
	var username=document.getElementById("username").value;
	$.ajax({
		url: $('#hdnContextPath').val() + '/users/queryUserName.action?username='+username,
		type: 'POST',
		success:function(data){
			if(data.status==500){
				SweetAlert.error(data.msg)
			}
		}
	})
} 
    
    var submitUser = function() {
    	$('#addUserForm').ajaxSubmit({
    		url: $('#hdnContextPath').val() + '/users/addUser.action',
    		type: 'POST',
    		success: function(data) {
    			
    			if (data.status == 200 && data.msg == 'OK') {
    				
    				alert('用户添加成功...');
    			} else if(data.status==500){
    				SweetAlert.error(data.msg);
    			}else{
    				 
    				alert('用户添加失败...')
    			}
    			
    			$("#userListMenu").click();
    		}
    	});
    }
    
    $('#addUserForm').validate({
    	errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "", // validate all fields including form hidden input
        rules: {
        	username: {
                required: true,
                rangelength: [1,8]
            },
            nickname: {
                required: true,
                rangelength: [1,8]
            },
            password: {
                required: true,
                rangelength: [1,15]
            }
        },
        messages: {
        	name: {
                required: "用户名不能为空.",
                rangelength: "用户名长度请控制在1-8位."
            },
            nickname: {
                required: "昵称不能为空.",
                rangelength: "昵称请控制在1-8位."
            },
            password: {
            	required: "初始密码不能为空.",
                rangelength: "初始密码请控制在1-15位."
            }
        },
        invalidHandler: function(event, validator) { //display error alert on form submit   
            $('.alert-danger', $('#addUserForm')).show();
        },

        highlight: function(element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },
        success: function(label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element.closest('#input-error'));
        },
        submitHandler: function(form) {
        	// FIXME
        	submitUser();
        }
    });
    
    
</script>
