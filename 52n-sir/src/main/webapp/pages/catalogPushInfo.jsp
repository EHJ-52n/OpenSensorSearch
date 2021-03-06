<%--

    ﻿Copyright (C) 2012 52°North Initiative for Geospatial Open Source Software GmbH

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@page import="org.n52.sir.client.Client"%>

<jsp:useBean id="getCatalogPushInfo"
	class="org.n52.sir.client.CatalogPushInfoBean" scope="application" />
<jsp:setProperty property="*" name="getCatalogPushInfo" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Catalog Push Information</title>

<jsp:include page="htmlHead.jsp"></jsp:include>

<script type="text/javascript">
	function scrollInfo() {
		textareaelem = document.getElementById("info");
		textareaelem.scrollTop = textareaelem.scrollHeight;
	}
</script>

</head>
<body onload="scrollInfo();">

	<div id="content">

		<jsp:include page="header.jsp" />
		<jsp:include page="../menu.jsp" />

		<div id="pageContent">

			<h2>Catalog Push Information</h2>
			<p>
				<textarea class="largeTextarea textareaBorder" id="info"
					readonly="readonly" rows="10" cols="10"><%=getCatalogPushInfo.getInfoString()%></textarea>
			</p>
			<p>
				(runtime only, maximum of last
				<%=getCatalogPushInfo.getMaxEventsCount()%>
				events)
			</p>

		</div>

	</div>

</body>
</html>