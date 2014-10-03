
<!-- <script>
	
</script> -->
<script src="/js/filemanager.js"></script>
<div id="file-manager">
	<input type="hidden" id="root" /> <input type="hidden" id="dir" />
	<div id="" class="fm-popover">
		<form action="/admin" method="post" enctype="multipart/form-data"
			id="file-upload-form">
			<label>Select file: <input id="file-name" type="file"
				name="file-data" /></label>
		</form>
	</div>
	<div id="fm-progress" class="fm-popover">
		<img src="/img/progress.gif" />
	</div>
	<ul id="file-list">

	</ul>
	<div id="fm-toolbar">
		<button id="fm-button-upload">upload</button>
		<button id="fm-button-delete">delete</button>
		<button id="fm-button-makedir">makedir</button>
		<button id="fm-button-geturl">geturl</button>
		<button id="fm-button-opendir">opendir</button>
	</div>
	<!-- <form action="/admin" method="post" enctype="multipart/form-data"
		id="myForm">
		<label>Select file: <input id="file-name" type="file"
			name="file-data" /></label>
	</form>
	<div id="upload">upload</div>
	<select id="files" multiple="multiple"
		style="width: 300px; height: 200px;">
		<option value="file1">file1.jsp</option>
		<option value="file2">file2.jsp</option>
		<option value="file3">file3.jsp</option>
		<option value="file1">file1.jsp</option>
		<option value="file2">file2.jsp</option>
		<option value="file3">file3.jsp</option>
		<option value="file1">file1.jsp</option>
		<option value="file2">file2.jsp</option>
		<option value="file3">file3.jsp</option>
		<option value="file1">file1.jsp</option>
		<option value="file2">file2.jsp</option>
		<option value="file3">file3.jsp</option>
	</select>
	<div id="delete-file">Delete</div> -->
</div>