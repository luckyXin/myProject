
(function($$) {
	function DataGrid(arrayProps, plugType) {
		var _this = this;
		this.showMode="list";
		this.handleFields;
		for (i in arrayProps) {
			if (i.charAt(0) != '_') {
				eval('this.' + i + ' = arrayProps[\'' + i + '\'];');
			}
		}
		this.m_objMenu = $$.FloatMenu.NewButton({m_strPlugName:"m_objMenu"});
		this.pageRowsCount = 15;
		this.isHandle = plugType; //插件类型（操作与展示）
		this.floatDiv;
		this.handleRowIndex=this.EditRowIndex =this.EditDataIndex = -1;
		this.selectedRow;
		this.autoRefreshPage = null;
		this.odoc = document;
		this.otable = this.odoc.createElement("TABLE");
		this.otbody = this.odoc.createElement("TBODY");
		$("#" + this.containersId).hide();
		this.operationImg = new Array();
		this.dataIcon = new Array();
		this.operationImg["Dept"] = this.rootPath + "images/icon/Department.GIF";
		this.operationImg["Unit"] = this.rootPath + "images/icon/unit.gif";
		if (undefined != this.colsTitle
				&& (undefined == this.colsWidth || this.colsWidth.length != this.colsTitle.length)) {
			this.colsWidth = new Array();
			for ( var i = 0; i < this.colsTitle.length; i++) {
				this.colsWidth[i] = Math.floor(100 / this.colsTitle.length)
						+ "%";
			}
		}
		CreateLoadingDiv();
		if (this.isHandle == true) {
			this.floatDiv = CreatFloatDiv();
		}
	};
	$$.DataGrid = {
		New : function(opts) {
			return new DataGrid(opts);
		},
		NewHandle : function(opts) {
			return new DataGrid(opts, true);
		},
		NewStatic : function(opts) {
			return new DataGrid(opts, false);
		}
	};
	return DataGrid;
})($$);