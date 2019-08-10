<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>

<script>
    KindEditor.ready(function(K) {
        K.create('#editor_id',{
            uploadJson:"${path}/article/upload",
            filePostName:"upload", //与上传文件的controller 参数名一致 默认imgFile
            allowFileManager:true,
            fileManagerJson:"${path}/article/showAllPicture"
        })
    });
</script>



<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>