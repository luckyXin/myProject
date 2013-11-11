var treeNowNodes;
function dFTreeDefaultLayout(arrayProps) {
    this.closeBrothers = false;
    for (i in arrayProps) {
        if (i.charAt(0) != '_') {
            eval('this.' + i + ' = arrayProps[\'' + i + '\'];');
        }
    }
}
dFTreeDefaultLayout.prototype._draw_tree = function(tree) {
    if (!getObjectById("dftree_" + tree.name)) {
        if (tree.parentName == null || tree.parentName == undefined || tree.parentName == "")
            document.write('<table class="root" id="dftree_' + tree.name + '" cellspacing="0" cellpadding="0"></table>');
        else {
            document.getElementById(tree.parentName).innerHTML = '<table class="root" id="dftree_' + tree.name + '" cellspacing="0" cellpadding="0"></table>';
            //var s = document.getElementById(tree.parentName).innerHTML;
        }
    }
}
dFTreeDefaultLayout.prototype._get_children_interval = function(node) {
    var startIndex = 0;
    var endIndex = 0;
    var nodeRow = getObjectById("n" + node.id);
    if (nodeRow) {
        startIndex = nodeRow.rowIndex + 1;
        var table = nodeRow.parentNode.parentNode;
        if (table) {
            endIndex = table.rows.length;
            var parent = node;
            var quit = false;
            while (!quit && parent) {
                var list = null;
                if (parent._parent) {
                    list = parent._parent._children;
                }
                else
                    list = node._myTree._roots;
                var parentIndex = node._myTree._searchNodeIn(parent.id, list);
                if (parentIndex < list.length - 1) {
                    var uncleRow = getObjectById("n" + list[parentIndex + 1].id);
                    if (uncleRow) {
                        endIndex = uncleRow.rowIndex;
                        quit = true;
                    }
                }
                parent = parent._parent;
            }
        }
    }
    return {
        startIndex: startIndex, count: (endIndex - startIndex)
    };
}
dFTreeDefaultLayout.prototype._draw_node = function(node) {
    var str;
    var div;
    var myPlus = node._properPlus();
    var myIcon = node._properIcon();
    var myPlusOnClick = node._myTree.name + '.getNodeById(\'' + node.id + '\').changeState();';
    var captionOnClickEvent = "";
    captionOnClickEvent = node._myTree.name + '.getNodeById(\'' + node.id + '\')._select(); ';
    if (node.onClick) {
        captionOnClickEvent += node.onClick;
    }
    else if (node.url && node.target) {
        captionOnClickEvent += 'window.open(\'' + node.url + '\',\'' + node.target + '\')';
    }
    else if (node.url) {
        captionOnClickEvent += 'window.location=\'' + node.url + '\'';
    }
    var plusEventHandler = function() {
        eval(myPlusOnClick);
    }
    var captionEventHandler = function() {
        eval(captionOnClickEvent);
    }
    
    var table = getObjectById("dftree_" + node._myTree.name);
    if (table) {
        var rowIndex = table.rows.length;
        var is_shown = true;
        if (node._parent != null) {
            var interval = this._get_children_interval(node._parent);
            rowIndex = interval.startIndex + interval.count;
            is_shown = node._parent._io;
        }
        var level = node._get_level();
        var must_clean_table = false;
        if (typeof (node._myTree._levelMax) == "undefined") {
            node._myTree._levelMax = level;
            must_clean_table = true;
        }
        else if (node._myTree._levelMax < level) {
            node._myTree._levelMax = level;
            must_clean_table = true;
        }
        var objN = table.insertRow(rowIndex);
        objN.id = 'n' + node.id;
        objN.className = 'son';
        if (!is_shown) {
            objN.style.display = "none";
        }
        for (i = 0; i < level; i++) {
            var c = objN.insertCell(i);
            c.className = "tc";
        }
        this._refresh_parent_vertical_lines(node, level, objN);
        var index = level;
        var objP = objN.insertCell(index++);
        objP.id = 'p' + node.id;
        objP.className = 'plus';
        objP.onclick = plusEventHandler;
        this._update_vertical_lines(node, objP);
        objP.innerHTML = myPlus;
        objP.title = node.title;
        if (node._myTree.useIcons) {
            objI = objN.insertCell(index++);
            objI.id = 'i' + node.id;
            objI.className = "icon";
            objI.style.width = '18px';
            if (node._myTree.useIcons)
                objI.innerHTML = myIcon;
            else
                objI.innerHTML = "";
        }
        objL = objN.insertCell(index++);
        objL.id = 'l' + node.id;
        objL.className = node.captionClass;
        if(tree.bNodeClick!=true || node.onClick==""){
            objL.style.cursor="default";
    	}else{
    		//手样式
            objL.style.cursor="pointer";
    	}
       
        objL.colSpan = 50;
       if(tree.bNodeClick==true && node.onClick!="")
        {
        	objL.onclick = captionEventHandler;
        }
        	
        objL.width = "100%";
        if (node._myTree.checkBox && node.checkBox=="true") {
        	if(node._myTree.findPath){
	            if ($(node._myTree.CheckValues).index(node.box_value) == -1)
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreePathBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\" > " + node.caption;
	            else
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' checked id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreePathBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\" > " + node.caption;
        	}
        	else{
	            if ($(node._myTree.CheckValues).index(node.box_value) == -1)
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreeBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\" > " + node.caption;
	            else
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' checked id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreeBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\" > " + node.caption;
            }
        }
        else
            objL.innerHTML = " " + node.caption;
        objL.title = node.title;
        if(undefined!=node._myTree.nodeHandIco && ""!=node._myTree.nodeHandIco){        
	        var objDiv=document.createElement("div");
	        $(objL).append(objDiv);
	        $(objDiv).addClass("treeNodeHand");
	        var objSpan=document.createElement("span")
	        $(objDiv).append(objSpan);
	        $(objSpan).text(node._myTree.nodeHandText);
	        //$(objDiv).text(node._myTree.nodeHandText);
	        $(objDiv).css("background-image",node._myTree.nodeHandIco);
	        $(objDiv).click(node._myTree.nodeHandClick);
	        $(objDiv).append(document.createElement("<input type='hidden' onchange=\"javascript:if(this.value==''){$(this).parent().find('span').text('"+node._myTree.nodeHandText+"');}else{$(this).parent().find('span').text(this.value);}\">"));
        }
        this._update_on_context_menu(node, objL);
        if (must_clean_table) {
            this._clean_table(table, node._myTree);
        }
    }
}
dFTree.prototype.SelectTreePathBox = function(nodeid, obj, par) {
	if(this.boxType=="radio"){
		return;
	}
	
	if(null!=treeNowNodes[nodeid] && undefined!=treeNowNodes[nodeid])
	{	
    var chieldid = treeNowNodes[nodeid]._childNodeId.split(",");
    for (var x = 0; x < chieldid.length; x++) {
        if (chieldid[x] != "") {
        	if(undefined!=document.getElementById("dtbox" + chieldid[x]) && undefined!=document.getElementById("dtbox" + chieldid[x])){
	            document.getElementById("dtbox" + chieldid[x]).checked = obj.checked;
	            this.SelectTreePathBox(+chieldid[x], obj, true);
            }
        }
    }
	}
   /* if (!par)
        this.CheckParentPathNode(treeNowNodes[nodeid],false);*/
}
dFTree.prototype.SelectTreeBox = function(nodeid, obj, par) {
	if(this.boxType=="radio"){
		return;
	}
    if (this._aNodes[nodeid]._childNodeId != undefined && this._aNodes[nodeid]._childNodeId != "") {
        var chieldid = this._aNodes[nodeid]._childNodeId.split(",");
        for (var x = 0; x < chieldid.length; x++) {
            if (chieldid[x] != "") {
            	try{
	            	if(document.getElementById("dtbox" + chieldid[x]) && undefined!=document.getElementById("dtbox" + chieldid[x])){
		                document.getElementById("dtbox" + chieldid[x]).checked = obj.checked;
		                this.SelectTreeBox(+chieldid[x], obj, true);
	                }
            	}
            	catch(e)
            	{}
            }
        }
    }
    if (!par)
        this.CheckParentNode(this._aNodes[nodeid], false);
}
dFTreeDefaultLayout.prototype._update_on_context_menu = function(node, objL) {
    if (node.onContextMenu != null) {
        var fctn = "function(evt) { evt = (evt) ? evt : ((event) ? event : null);if (evt.preventDefault) {evt.preventDefault();}evt.returnValue = false;" + node.onContextMenu + "}";
        if (!global_ie) {
            eval("objL.addEventListener('contextmenu', " + fctn + ", false);");
        }
        else
            eval("objL.oncontextmenu = " + fctn + ";");
    }
}
dFTreeDefaultLayout.prototype._refresh = function(node) {
    var objN = getObjectById("n" + node.id);
    var objP = getObjectById("p" + node.id);
    var objL = getObjectById("l" + node.id);
    var objI = getObjectById("i" + node.id);
    if (objN != null) {
        var table = objN.parentNode.parentNode;
        var must_clean_table = false;
        if (!node._io) {
            var level = node._get_level();
            var interval = this._get_children_interval(node);
            rowIndex = interval.startIndex + interval.count - 1;
            for (i = 0; i < interval.count; i++, rowIndex--) {
                var childRow = table.rows[rowIndex];
                if (childRow.style.display != "none") {
                    childRow.style.display = "none";
                    must_clean_table = true;
                }
            }
        }
        else {
            for (i in node._children) {
                var child = getObjectById("n" + node._children[i].id);
                if (child) {
                    if (child.style.display != "") {
                        child.style.display = "";
                        must_clean_table = true;
                    }
                }
            }
        }
        if (must_clean_table) {
            this._clean_table(table, node._myTree);
        }
        objP.innerHTML = node._properPlus();
        if (node._myTree.checkBox && node.checkBox=="true") {
        	if(node._myTree.findPath){
	            if (document.getElementById('dtbox' + node.id).checked)
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreePathBox('" + node.id + "',this,false);\" checked value=\"" + node.box_value + "\"> " + node.caption;
	            else
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreePathBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\"> " + node.caption;
        	}
        	else{
	            if (document.getElementById('dtbox' + node.id).checked)
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreeBox('" + node.id + "',this,false);\" checked value=\"" + node.box_value + "\"> " + node.caption;
	            else
	                objL.innerHTML = "<input type='"+node._myTree.boxType+"' id='dtbox" + node.id + "' name='dtbox' onclick=\"" + dTreeName + ".SelectTreeBox('" + node.id + "',this,false);\" value=\"" + node.box_value + "\"> " + node.caption;
            }
        }
        else
            objL.innerHTML = " " + node.caption;
        
        if(undefined!=node._myTree.nodeHandIco && ""!=node._myTree.nodeHandIco){        
	        var objDiv=document.createElement("div");
	        $(objL).append(objDiv);
	        $(objDiv).addClass("treeNodeHand");
	        var objSpan=document.createElement("span")
	        $(objDiv).append(objSpan);
	        $(objSpan).text(node._myTree.nodeHandText);
	        //$(objDiv).text(node._myTree.nodeHandText);
	        $(objDiv).css("background-image",node._myTree.nodeHandIco);
	        $(objDiv).click(node._myTree.nodeHandClick);
	        $(objDiv).append(document.createElement("<input type='hidden' onchange=\"javascript:if(this.value==''){$(this).parent().find('span').text('"+node._myTree.nodeHandText+"');}else{$(this).parent().find('span').text(this.value);}\">"));
        }
        
        if (objI != null) {
            if (node._myTree.useIcons)
                objI.innerHTML = node._properIcon();
            else
                objI.innerHTML = "";
        }
        var level = node._get_level();
        this._refresh_parent_vertical_lines(node, level, objN);
        this._update_on_context_menu(node, objL);
    }
}
dFTreeDefaultLayout.prototype._refresh_parent_vertical_lines = function(node, level, row) {
    var parent = node._parent;
    var i = level;
    while (i > 0) {
        i--;
        if (parent != null) {
            this._update_vertical_lines(parent, row.cells[i]);
            parent = parent._parent;
        }
    }
}
dFTreeDefaultLayout.prototype._update_vertical_lines = function(node, objV) {
    var last = node._is_last();
    if (node._myTree.useIcons && !last) {
        objV.style.background = "transparent url(" + node._myTree.icons.line + ") repeat-y scroll left top";
    }
    else {
        objV.style.background = "";
    }
}
dFTreeDefaultLayout.prototype._select_node = function(node) {
    var objL;
    objL = getObjectById("l" + node.id);
    if (objL) {
        try {
            var dt = new Date();
            var temp = dt.getYear() + "" + dt.getMonth() + "" + dt.getDay() + "" + dt.getHours() + "" + dt.getMinutes() + "" + dt.getSeconds() + "" + dt.getMilliseconds();
            var tempId = getParentToChildNode(node);
            for (var x = tempId.length - 1; x >= 0; x--)
                TreeChangeNavigation(tempId[x].caption, "l" + tempId[x].id, temp);
        }
        catch (e) {
        }
        objL.className = 'sl';
    }
}
function getParentToChildNode(node) {
    var nodeid = new Array();
    nodeid[nodeid.length] = node;
    while (node._parent != null) {
        nodeid[nodeid.length] = node._parent;
        node = node._parent;
    }
    return nodeid;
}
dFTreeDefaultLayout.prototype._unselect_node = function(node) {
    var objL = getObjectById("l" + node.id);
    if (objL) {
        objL.className = node.captionClass;
    }
}
dFTreeDefaultLayout.prototype._erase_node = function(node) {
    var objN = getObjectById("n" + node.id);
    if (objN) {
        var table = objN.parentNode.parentNode;
        table.deleteRow(objN.rowIndex);
    }
}
dFTreeDefaultLayout.prototype._clean_table = function(table, tree) {
    var cols = 0;
    for (i = 0; i < table.rows.length; i++) {
        var row = table.rows[i];
        if (row.style.display != "none") {
            if (cols < row.cells.length) {
                cols = row.cells.length;
            }
        }
    }
    var level = cols - 3;
    tree._levelMax = level;
    for (i = 0; i < table.rows.length; i++) {
        var row = table.rows[i];
        if (row.style.display != "none") {
            var lastIndex = row.cells.length - 1;
            //var cell = row.cells[lastIndex];
            row.cells[lastIndex].colSpan = cols - lastIndex;
        }
    }
}
dNode = function(arrayProps) {
    this.id;
    this.caption;
    this.box_value = "";
    this.postvalue;
    this.url;
    this.target;
    this.onClick;
    this.onOpen;
    this.onClose;
    this.onFirstOpen;
    this.onContextMenu;
    this.iconClosed;
    this.iconOpen;
    this.postpage;
    this.runJS = true;
    this.plusSign = true;
    this.isFolder = true;
    this.captionClass = 'l';
    this._opened = false;
    this._io = false;
    this._drawn = false;
    this._undrawn_children = false;
    this._children = [];
    this._parent;
    this._myTree;
    this._title;
    this._childNodeId = "";
    this.treetype;
    this.checkBox="true";
    for (i in arrayProps) {
        if (i.charAt(0) != '_') {
            eval('this.' + i + ' = arrayProps[\'' + i + '\'];');
        }
    }
    this.iRecursion=0;
}
dNode.prototype.showNode = function() {
    if (this._io) {
    }
    else {
        this.AjaxGetChildNode(this, true);
    }
}
dNode.prototype.changeState = function() {
    if (this._io) {
        this.close();
    }
    else {
        this.AjaxGetChildNode(this, true);
    }
    if (this._myTree) {
        this._myTree._setCookie();
    }
}
var oldNode = null;
var nowNode = null;
var saveNode = new Array();
var Ajaxing = false;
var IsRef = false;
var x = 0;
var thread = null;
var handNode = 0;
dNode.prototype.AjaxGetChildNode = function(node, cache, isref, iscompulsorychild) {
    nowNode = node;
    if (isref != undefined && isref == true && iscompulsorychild == true) {
        handNode = node._children.length;
    }
    if (isref == true)
        IsRef = isref;
    else
        IsRef = false;
    if (cache)
        oldNode = node;
    saveNode = new Array();
    if (node.postpage != undefined && node.postpage != "") {
        Ajaxing = true;
        var path="piParentID="+node.id+"&pstrParentValue="+ node.postvalue;
        $.ajax({
            url: node.postpage,
            type: "POST",
            dataType: "json",
            data: path,
            success: function(json) {
            	if(null!=tree && undefined!=tree)
            	{
            		tree.ParseXML(json, cache, iscompulsorychild,node);	
            	}	
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                AjaxError(XMLHttpRequest, textStatus, errorThrown);
            }
        })
    }
    else{
    	Ajaxing = false;
        this.open();
    }
}
dFTree.prototype.ParseXML = function(json, cache, iscompulsorychild,node) {
    if (iscompulsorychild == false) {
        $(json).each(function() {
        	var checkbox="true";
        	if(this.checkBox!=undefined){
        			checkbox=this.checkBox;
			}
            tree.autoReplaceNode(new dNode({ id: tree.allNodeNum, caption: this.Caption, isFolder: 0, treetype: this.Ico, onClick: this.OnClick, title: this.Alt, box_value: this.BoxValue, postvalue: this.PostValue, postpage: this.PostPage ,checkBox:checkbox}), this.ParentID);
        });
    }
    else {
        $(json).each(function() {
        	var checkbox="true";
        	if(this.checkBox!=undefined){
        		checkbox=this.checkBox;
			}
            tree.autoAdd(new dNode({ id: tree.allNodeNum, caption: this.Caption, isFolder: 0, treetype: this.Ico, onClick: this.OnClick, title: this.Alt, box_value: this.BoxValue, postvalue: this.PostValue, postpage: this.PostPage ,checkBox:checkbox}), this.ParentID);
        });
    }
    this.autoDeleteChildNode(nowNode, cache);
    this._drawn = false;
    Ajaxing = false;
    if(this.bShowAllNode){
    	node.iRecursion=0;
    	this.CacheAllNode(node);
    	this.draw();
    	node.open();
    }
    else if (cache) {
        x = 0;
        thread = setInterval(this.CacheNode, 50);
        this.draw();
        oldNode.open();
    }
    if (IsRef == true) {
        this.draw();
    }
    if (iscompulsorychild != undefined && iscompulsorychild == true) {
        if (handNode != 0) {
            if (handNode > nowNode._children.length) {
                if (nowNode._children.length != 0)
                    nowNode._children[nowNode._children.length - 1].AjaxGetChildNode(nowNode._children[nowNode._children.length - 1], false, true, false)
            }
            else if (handNode < nowNode._children.length)
                nowNode._children[nowNode._children.length - 2].AjaxGetChildNode(nowNode._children[nowNode._children.length - 2], false, true, false)
        }

    }
    else if (iscompulsorychild == false) {
        $("#p" + nowNode.id).click();
        $("#p" + nowNode.id).click();
    }
}
dFTree.prototype.CacheNode = function() {
    if (x < oldNode._children.length) {
        if (!Ajaxing) {
            Ajaxing = true;
            x++;
            oldNode._children[x - 1].AjaxGetChildNode(oldNode._children[x - 1], false);
        }
    }
    else {
        clearInterval(thread);
    }
}
dFTree.prototype.CacheAllNode = function(node) {
    if (node.iRecursion < node._children.length) {
        if (!Ajaxing) {
            Ajaxing = true;
            node.iRecursion++;
            node._children[node.iRecursion-1].AjaxGetChildNode(node._children[node.iRecursion-1], true);
        }
    	setTimeout(function(){try{tree.CacheAllNode(node)}catch(e){}},50);
    }
    else{
    	if(node._parent!=undefined){
    		setTimeout(function(){try{tree.CacheAllNode(node._parent)}catch(e){}},50);
    	}
    }
}
dFTree.prototype.refreshNode = function(node) {
    node._myTree.layout._refresh(node);
}
dFTree.prototype.autoAdd = function(node, pid) {
	this.allNodeNum+1;
    oldnode = this.getNodeByCaption(node);
    if (oldnode == null || oldnode == undefined) {
        this.add(node, pid);
        saveNode[saveNode.length] = node.id + "";
    }
    else if (node.caption == oldnode.caption && node.postvalue == oldnode.postvalue && node.onClick == oldnode.onClick) {
        saveNode[saveNode.length] = oldnode.id + "";
    }
    else {
        this.RemoveNode(oldnode.id, true);
        this.add(node, pid);
        saveNode[saveNode.length] = node.id + "";
    }
}
dFTree.prototype.autoReplaceNode = function(node, pid) {
	this.allNodeNum+1;
    oldnode = this.getNodeByCaption(node);
    if (oldnode != null && oldnode != undefined) {
        this.RemoveNode(oldnode.id, true);
        this.add(node, pid);
        saveNode[saveNode.length] = node.id + "";
    }
    else {
        this.add(node, pid);
        saveNode[saveNode.length] = node.id + "";
    }
}
dFTree.prototype.getNodeByCaption = function(node) {
    var obj = null;
    for (var x = 0; x < this._aNodes.length; x++) {
        if (this._aNodes[x].caption == node.caption && this._aNodes[x].postvalue == node.postvalue && this._aNodes[x].treetype == node.treetype)
            obj = this._aNodes[x];
        if (node.caption == this._aNodes[x].caption && node.postvalue == this._aNodes[x].postvalue && node.onClick == this._aNodes[x].onClick)
            return this._aNodes[x];
    }
    return obj;
}
dFTree.prototype.autoDeleteChildNode = function(node, cache) {
    var nowChildId = node._childNodeId.split(",");
    for (var x = 0; x < nowChildId.length; x++) {
        if (nowChildId[x] != "") {
            if ($(saveNode).index(nowChildId[x]) == -1) {
                if (IsRef == true)
                    this.RemoveNode(nowChildId[x], true);
                else
                    this.RemoveNode(nowChildId[x], cache);
            }
        }
    }
}
dFTree.prototype.RemoveNodeIdByPostValue = function(value) {
    for (var x = 0; x < this._aNodes.length; x++) {
        if (this._aNodes[x].postvalue != undefined && this._aNodes[x].postvalue != "") {
            if (this._aNodes[x].postvalue == value)
                this.RemoveNode(this._aNodes[x].id, true);
        }
    }
}
dFTree.prototype.GetCodeByPostValue = function(value) {
    for (var x = 0; x < this._aNodes.length; x++) {
        if (this._aNodes[x].postvalue != undefined && this._aNodes[x].postvalue + "" != "") {
            if (this._aNodes[x].postvalue == value)
                return x;
        }
    }
}
dFTree.prototype.GetNodeByPostValue = function(value) {
    for (var x = 0; x < this._aNodes.length; x++) {
        if (this._aNodes[x].postvalue != undefined && this._aNodes[x].postvalue + "" != "") {
            if (this._aNodes[x].postvalue == value)
                return this._aNodes[x];
        }
    }
}
dNode.prototype.open = function() {
    if (!this._io) {
        if (this._parent != null && this._myTree.useIcons && this._myTree.layout.closeBrothers) {
            this._myTree._closeBranch(this._parent._children);
        }
        this._io = true;
        if (!this._opened) {
            if (this.runJS && this.onFirstOpen != null) {
                eval(this.onFirstOpen);
            }
            if (this._myTree.isLazy) {
                this._myTree._drawBranch(this._children);
            }
        }
        else if (this.runJS && this.onOpen != null) {
            eval(this.onOpen);
        }
        this._opened = true;
        if (this._undrawn_children) {
            this._undrawn_children = false;
            this._myTree._drawBranch(this._children);
        }
        this._refresh();
    }
}
dNode.prototype.expand = function() {
    this.open();
    for (i in this._children) {
        if (this._children[i] != null) this._children[i].expand();
    }
}
dNode.prototype.close = function() {
    if (this._io) {
        this._myTree._closeBranch(this._children);
        if (this.runJS && this.onClose != null) {
            eval(this.onClose);
        }
        this._io = false;
        this._refresh();
    }
}
dNode.prototype.collapse = function() {
    this.close();
}
dNode.prototype.alter = function(arrayProps) {
    for (i in arrayProps) {
        if (i != 'id' && i.charAt(0) != '_') {
            eval('this.' + i + ' = arrayProps[\'' + i + '\'];');
        }
    }
    for (i in this._myTree._aNodes) {
        if (this._myTree._aNodes[i] != null) this._myTree._aNodes[i]._refresh();
    }
}

dNode.prototype._refresh = function() {
    this._myTree.layout._refresh(this);
}
dNode.prototype._is_last = function() {
    var last = true;
    if (this._parent) {
        index = this._myTree._searchNodeIn(this.id, this._parent._children);
        last = (index == this._parent._children.length - 1);
    } else {
        index = this._myTree._searchNodeIn(this.id, this._myTree._roots);
        last = (index == this._myTree._roots.length - 1);
    }
    return last;
}
dNode.prototype._properPlus = function() {
    var last = this._is_last();
    if (this._children.length == 0) {
        myPlusSign = false;
    }
    else {
        myPlusSign = this.plusSign;
    }
    if (!this._io) {
        if (this._myTree.useIcons) {
            if (!last) {
                return (myPlusSign) ? imageHTML(this._myTree.icons.plus) : imageHTML(this._myTree.icons.join);
            }
            else {
                return (myPlusSign) ? imageHTML(this._myTree.icons.plusBottom) : imageHTML(this._myTree.icons.joinBottom);
            }
        }
        else {
            return (myPlusSign) ? "+" : "";
        }
    }
    else {
        if (this._myTree.useIcons) {
            if (!last) {
                return (myPlusSign) ? imageHTML(this._myTree.icons.minus) : imageHTML(this._myTree.icons.join);
            }
            else {
                return (myPlusSign) ? imageHTML(this._myTree.icons.minusBottom) : imageHTML(this._myTree.icons.joinBottom);
            }
        }
        else {
            return (myPlusSign) ? "-" : "";
        }
    }
}
dNode.prototype._properIcon = function() {
    if (!this._myTree.useIcons) {
        return "";
    }
    if (!this._io) {
        if (this._iconClosed) {
            return imageHTML(this._iconClosed);
        }
        else if (!this.isFolder) {
            if (this.treetype == "Department")
                return imageHTML(this._myTree.icons.Department);
            if (this.treetype == "Unit")
                return imageHTML(this._myTree.icons.Unit);
            else if (this.treetype == "user")
                return imageHTML(this._myTree.icons.user);
            else if (this.treetype == "sort")
                return imageHTML(this._myTree.icons.sort);
            else if (this.treetype == "Folder")
                return imageHTML(this._myTree.icons.folder);
            else if (this.treetype == "module")
                return imageHTML(this._myTree.icons.module);
            else if (this.treetype == "table")
                return imageHTML(this._myTree.icons.table);
            else if (this.treetype == "template")
                return imageHTML(this._myTree.icons.template);
            else if (this.treetype == "Dictionary")
                return imageHTML(this._myTree.icons.Dictionary);
            else if (this.treetype == "DictionaryItem")
                return imageHTML(this._myTree.icons.DictionaryItem);
            else if (this.treetype == "Template")
                return imageHTML(this._myTree.icons.Template);
            else if (this.treetype == "Relation")
                return imageHTML(this._myTree.icons.Relation);
            else
                return imageHTML(this._myTree.icons.page);
        }
        else {
            return imageHTML(this._myTree.icons.folder);
        }
    }
    else {
        if (this._iconOpen) {
            return imageHTML(this._iconOpen);
        }
        else if (!this.isFolder) {
            if (this.treetype == "Department")
                return imageHTML(this._myTree.icons.Departmentopen);
            if (this.treetype == "Unit")
                return imageHTML(this._myTree.icons.Unitopen);
            else if (this.treetype == "user")
                return imageHTML(this._myTree.icons.useropen);
            else if (this.treetype == "sort")
                return imageHTML(this._myTree.icons.sortopen);
            else if (this.treetype == "Folder")
                return imageHTML(this._myTree.icons.folderOpen);
            else if (this.treetype == "module")
                return imageHTML(this._myTree.icons.moduleOpen);
            else if (this.treetype == "table")
                return imageHTML(this._myTree.icons.tableOpen);
            else if (this.treetype == "template")
                return imageHTML(this._myTree.icons.templateOpen);
            else if (this.treetype == "DictionaryItem")
                return imageHTML(this._myTree.icons.DictionaryItemopen);
            else if (this.treetype == "Dictionary")
                return imageHTML(this._myTree.icons.Dictionaryopen);
            else if (this.treetype == "Template")
                return imageHTML(this._myTree.icons.Template);
            else if (this.treetype == "Relation")
                return imageHTML(this._myTree.icons.Relation);
            else
                return imageHTML(this._myTree.icons.page);
        }
        else {
            return imageHTML(this._myTree.icons.folderOpen);
        }
    }
}
dNode.prototype._select = function() {
    if (this._myTree._selected) {
        this._myTree._selected._unselect();
    }
    this._myTree._selected = this;

    this._myTree.layout._select_node(this);
}
dNode.prototype._unselect = function() {
    this._myTree._lastSelected = this._myTree._selected;
    this._myTree._selected = null;

    this._myTree.layout._unselect_node(this);
}
dNode.prototype._draw = function() {
    if (!this._myTree._drawn) return;
    if (this._drawn) return;
    this._drawn = true;

    this._myTree.layout._draw_node(this);
}
dNode.prototype._erase = function() {
    this._myTree.layout._erase_node(this);
}
dNode.prototype._get_level = function() {
    level = 0;
    var parent = this._parent;
    while (parent != null) {
        level++;
        parent = parent._parent;
    }
    return level;
}
dTreeName = "";
function dFTree(arrayProps) {
    this.name;      //the value of this must be the name of the object
    dTreeName = arrayProps.name;
    this.is_dynamic = true;   //tree is dynamic, i.e. updated on the fly
    this.followCookies = true; //use previous state (o/c) of nodes
    this.useIcons = false;     //use icons or not
    this.isLazy = false;   //lazy creation: HTML objects are only created when the parent is opened
    this.checkBox = false;
    this.findPath=false;
    this.nodeHandIco;
    this.nodeHandClick;
    this.nodeHandText="";
    this.parentName;
    this.postpage;
    this.allNodeNum = 0;
    this.CheckValues = new Array(); //初始时选中的复选框值
    this.boxType="checkbox";
    if (!arrayProps.layout) {
        this.layout = new dFTreeDefaultLayout({});
    }
    iconPath = (arrayProps['icondir'] != null) ? arrayProps['icondir'] : '';
    this.icons = {
        line: iconPath + '/foldertree_line.gif',
        join: iconPath + '/foldertree_join.gif',
        joinBottom: iconPath + '/foldertree_joinbottom.gif',
        plus: iconPath + '/foldertree_plus.gif',
        plusBottom: iconPath + '/foldertree_plusbottom.gif',
        minus: iconPath + '/foldertree_minus.gif',
        minusBottom: iconPath + '/foldertree_minusbottom.gif',
        
        page: iconPath + '/foldertree_page.gif',
        folder: iconPath + '/foldertree_folder.gif',
        folderOpen: iconPath + '/foldertree_folderopen.gif',
        Department: iconPath + '/Department.GIF',
        Departmentopen: iconPath + '/Departmentopen.gif',
        Unit: iconPath + '/unit.gif',
        Unitopen: iconPath + '/unitopen.gif',
        sort: iconPath + '/sort.gif',
        sortopen: iconPath + '/sortopen.gif',
        user: iconPath + '/user.gif',
        useropen: iconPath + '/useropen.gif',
        module: iconPath + '/module.gif',
        moduleOpen: iconPath + '/openmodule.gif',
        table: iconPath + '/table.gif',
        tableOpen: iconPath + '/table.gif',
        template:iconPath + '/opentemplate.gif',
        DictionaryItem:iconPath+'/DictionaryType.gif',
        DictionaryItemopen:iconPath+'/DictionaryTypeopen.gif',
        Dictionary:iconPath+'/Dictionary.gif',
        Dictionaryopen:iconPath+'/Dictionaryopen.gif',
        Template:iconPath+'/Template.gif',
       	Relation:iconPath+'/Relation.gif'
        
    };
    this._roots = []; //reference to root nodes
    this._aNodes = [];
    this._lastSelected; //The last selected node
    this._selected=null; //The actual selected node
    this._drawn = false;
	this.bShowAllNode=false;
	this.bNodeClick=true;
    for (i in arrayProps) {
        if (i.charAt(0) != '_') {
            eval('this.' + i + ' = arrayProps[\'' + i + '\'];');
        }
    }
}
dFTree.prototype.getCheckedBox=function(){
	var strVal="",strText="",strInfo="",strval,strtext,strinfo;
	var nodes=[];
	for(var x=0;x<this._aNodes.length;x++){
		if($("#n"+this._aNodes[x].id).css("display")=="block"){
			if($("#l"+this._aNodes[x].id + " > input:checkbox")[0].checked){
				strval=$("#l"+this._aNodes[x].id).find("input:checkbox").val();
				strtext=$("#l"+this._aNodes[x].id + " > input:checkbox")[0].nextSibling.data.replace(" ", "");
				strinfo=$("#l"+this._aNodes[x].id +" > div").find("input:hidden").val();
				nodes[nodes.length]={"value":strval,"text":strtext,"info":strinfo}
				strVal+=strval+",";
				strText+=strtext+",";
				strInfo+=strinfo+",";
				
			}
		}
	}
	return ({"value":strVal,"text":strText,"info":strInfo,"nodes":nodes});
}
dFTree.prototype.draw = function(isreadysub, isshowonenode,isallshownode) {
    if (this._drawn) return;
    if(undefined!=isallshownode)
    	this.bShowAllNode=isallshownode;
    this._drawn = true;
    this.layout._draw_tree(this);
    if (this._roots.length > 0) {
        //this._getCookie(); //读取缓存
        treeNowNodes = this._aNodes;
        this._drawBranch(this._roots);
        if (isreadysub == true) {
        	this._aNodes.iRecursion=0;
        	this.RecursionGetChildNode(this._aNodes);
            /*for (var x = 0; x < this._aNodes.length; x++) {
		    	if(Ajaxing==false)
		        	this._aNodes[x].AjaxGetChildNode(this._aNodes[x], false);
		    }*/
        }
        if (isshowonenode == true) {
            $('#l0').click()
            this.getNodeById('0').changeState();
        }
    }
}
dFTree.prototype.RecursionGetChildNode=function(mainnodes){
	if (mainnodes.iRecursion < mainnodes.length) {
    	if(Ajaxing==false){
    		mainnodes.iRecursion++;
        	mainnodes[mainnodes.iRecursion-1].AjaxGetChildNode(mainnodes[mainnodes.iRecursion-1], false);
    	}
    	if (tree!=null && mainnodes.iRecursion < mainnodes.length)
    		setTimeout(function(){try{tree.RecursionGetChildNode(mainnodes)}catch(e){}},50);
    }
	return;
}
dFTree.prototype.RemoveNode = function(id, deleteself) {
    if (id != "") {
        var index = -1;
        var deletenode = new Array();
        var deleteid = new Array();
        for (var x = 0; x < this._aNodes.length; x++) {
            if (this._aNodes[x].id == id) {
                if (deleteself) {
                    deletenode[deletenode.length] = this._aNodes[x];
                    deleteid[deleteid.length] = x;
                    this._aNodes[x]._childNodeId = "";
                    this._aNodes[x]._children.length = 0;
                    this._aNodes[this._searchNodeIn(this._aNodes[x]._parent.id, this._aNodes)]._childNodeId = this._aNodes[this._searchNodeIn(this._aNodes[x]._parent.id, this._aNodes)]._childNodeId.replace(id + ",", "");
                    var i = this._searchNodeIn(id, this._aNodes[this._searchNodeIn(this._aNodes[x]._parent.id, this._aNodes)]._children);
                    this._aNodes[this._searchNodeIn(this._aNodes[x]._parent.id, this._aNodes)]._children.splice(i, 1);
                    var childstr = this._aNodes[this._searchNodeIn(this._aNodes[x]._parent.id, this._aNodes)]._childNodeId.match(/\d*,$/);
                    if (childstr != null && childstr.length > 0) {
                        this._aNodes[this._searchNodeIn(childstr[0].replace(",", ""), this._aNodes)]._is_last();
                        this._aNodes[this._searchNodeIn(childstr[0].replace(",", ""), this._aNodes)]._myTree.layout._refresh(this._aNodes[this._searchNodeIn(childstr[0].replace(",", ""), this._aNodes)]);

                    }
                }
                else {
                    this._aNodes[x]._childNodeId = "";
                    this._aNodes[x]._children.length = 0;
                    deletenode[deletenode.length] = this._aNodes[x];
                    deleteid[deleteid.length] = x;
                }
            }
            else if ($(deletenode).index(this._aNodes[x]._parent) != -1) {
                deletenode[deletenode.length] = this._aNodes[x];
                deleteid[deleteid.length] = x;
            }
        }
        var objN = null;
        for (var x = deletenode.length - 1; x >= 0; x--) {
            if (x == 0) {
                if (deleteself) {
                    objN = getObjectById("n" + deletenode[x].id);
                    if(objN)
                        objN.parentNode.removeChild(objN);
                    this._aNodes.splice(deleteid[x], 1);
                }
                else
                    continue;
            }
            else {
                objN = getObjectById("n" + deletenode[x].id);
                if(objN)
                    objN.parentNode.removeChild(objN);
                this._aNodes.splice(deleteid[x], 1);
            }
        }
        this._drawn = false;
        this.draw();
                if(deletenode.length>0)
                    this._setCookie();
    }
}
dFTree.prototype.CheckTreeBox = function(valuestr) {
    var values = valuestr.split(",");
    for (var x = 0; x < values.length; x++) {
        if (values[x] != "") {
            for (var y = 0; y < this._aNodes.length; y++) {
                if (values[x] == this._aNodes[y].box_value) {
                    try {
                        document.getElementById("dtbox" + this._aNodes[y].id).checked = true;
                        lastobj = document.getElementById("dtbox" + this._aNodes[y].id);
                        break;
                    }
                    catch (e) {
                        setTimeout("tree.CheckTreeBox('" + valuestr + "')", 5000);
                    }
                }
            }
        }
    }
    this.AutoCheckedParentNode();
}
var lastnodes = new Array();
dFTree.prototype.AutoCheckedParentNode = function() {
    for (var x = 0; x < this._aNodes.length; x++) {
        if (this._aNodes[x]._childNodeId == "")
            lastnodes[lastnodes.length] = this._aNodes[x];
    }
    for (var x = 0; x < lastnodes.length; x++) {
        this.CheckParentNode(lastnodes[x], true);
    }
}
dFTree.prototype.CheckParentPathNode = function(node, alltype) {
    if (null!=node && undefined!=node) {
    if(null!=node._parent && undefined!=node._parent)
     {
        var parentnode = node._parent;
        var chieldnodeid = parentnode._childNodeId.split(",");
        for (var x = 0; x < chieldnodeid.length; x++) {
            if (chieldnodeid[x] != "") {
                if (undefined!=document.getElementById("dtbox" + parentnode.id) && !document.getElementById("dtbox" + chieldnodeid[x]).checked) {
                    if (alltype)
                        this.RemoveArrayNode(chieldnodeid[x]);
                    /*if (!document.getElementById("dtbox" + node.id).checked)
                        document.getElementById("dtbox" + parentnode.id).checked = false;
                    break;*/
                    document.getElementById("dtbox" + parentnode.id).checked = false;
                }
                /*else if (!document.getElementById("dtbox" + chieldnodeid[x]).checked) {
                    if (alltype)
                        this.RemoveArrayNode(chieldnodeid[x]);
                }*/
                else{
                	if(undefined!=document.getElementById("dtbox" + parentnode.id)){
                		document.getElementById("dtbox" + parentnode.id).checked = true;
                	}
                	break;
                }
            }
        }
        this.CheckParentPathNode(parentnode, alltype);
      }
    }
}
dFTree.prototype.CheckParentNode = function(node, alltype) {
    if (node._parent != null && node._parent != undefined) {
        var parentnode = node._parent;
        if (parentnode._childNodeId != undefined && parentnode._childNodeId != "") {
            var chieldnodeid = parentnode._childNodeId.split(",");
            for (var x = 0; x < chieldnodeid.length; x++) {
                if (chieldnodeid[x] != "") {
                    if (undefined!=document.getElementById("dtbox" + chieldnodeid[x]) && !document.getElementById("dtbox" + chieldnodeid[x]).checked) {
                        if (alltype)
                            this.RemoveArrayNode(chieldnodeid[x]);
                        if (undefined!=document.getElementById("dtbox" + parentnode.id) && undefined!=document.getElementById("dtbox" + node.id) && !document.getElementById("dtbox" + node.id).checked)
                            document.getElementById("dtbox" + parentnode.id).checked = false;
                        this.CheckParentNode(parentnode, alltype);
                        return;
                    }
                    else if (undefined!=document.getElementById("dtbox" + chieldnodeid[x]) && !document.getElementById("dtbox" + chieldnodeid[x]).checked) {
                        if (alltype)
                            this.RemoveArrayNode(chieldnodeid[x]);
                    }
                }
            }
        }
        if(undefined!=document.getElementById("dtbox" + parentnode.id)){
        	document.getElementById("dtbox" + parentnode.id).checked = true;
        }
        this.CheckParentNode(parentnode, alltype);
    }
}

dFTree.prototype.RemoveArrayNode = function(nodeid) {
    for (var x = 0; x < lastnodes.length; x++) {
        if (lastnodes[x] == nodeid)
            return lastnodes.remove(x);
    }
    return null;
}
dFTree.prototype._drawBranch = function(childrenArray) {
    var a = 0;
    for (a; a < childrenArray.length; a++) {
        childrenArray[a]._draw();
        if (!this.isLazy || childrenArray[a]._io) {
            this._drawBranch(childrenArray[a]._children);
        }
    }
}
dFTree.prototype._closeBranch = function(childrenArray) {
    for (i in childrenArray) {
        if (childrenArray[i] != null) childrenArray[i].close();
    }
}
dFTree.prototype.collapseAll = function() {
    for (i in this._roots) {
        if (this._roots[i] != null) this._roots[i].collapse();
    }
}
dFTree.prototype.expandAll = function() {
    for (i in this._roots) {
        if (this._roots[i] != null) this._roots[i].expand();
    }
}
dFTree.prototype.add = function(node, pid) {
    if (node.postpage == undefined) {
        node.postpage = this.postpage;
    }
    var auxPos;
    var addNode = false;
    if (typeof (auxPos = this._searchNode(node.id)) != "number") {
        this.allNodeNum += 1;
        if (typeof (auxPos = this._searchNode(pid)) == "number") {
            node._parent = this._aNodes[auxPos];
            this._aNodes[auxPos]._childNodeId += node.id + ",";
            this._aNodes[auxPos]._children[this._aNodes[auxPos]._children.length] = node;
            addNode = true;
        }
        else {
            this._roots[this._roots.length] = node;
            addNode = true;
        }
        if (addNode) {
            this._aNodes[this._aNodes.length] = node;
            node._myTree = this;
            if (this.is_dynamic) {
                if (!this.isLazy || (node._parent && node._parent._io && node._parent._drawn)) node._draw();
                if (node._parent && node._parent._drawn) {
                    node._parent._refresh();
                    if (node._parent._children.length > 1) {
                        var n = node._parent._children[node._parent._children.length - 2];
                        if (n._drawn) n._refresh();
                    }
                }
            }
        }
    }
}

dFTree.prototype.remove = function(node) {
    var childPos;
    if (typeof (childPos = this._searchNode(node.id)) != "number") {
        alert("Node '" + node.id + " not found in " + this.name);
    }
    else {
        for (i = node._children.length - 1; i >= 0; i--) {
            if (typeof (node._children[i]) == "object") {
                this.remove(node._children[i]);
            }
        }

        node._erase();

        var parent = node._parent;
        var list = null;
        if (parent === null) {
            list = this._roots;
        }
        else {
            list = parent._children;
        }
        if (list !== null) {
            var auxPos = this._searchNodeIn(node.id, list);
            if (typeof (auxPos) == "number") {
                list.splice(auxPos, 1);
            }
        }
        node._parent = null;
        this._aNodes.splice(childPos, 1);

        if (parent !== null) {
            parent._refresh();
            if (parent._children.length >= 1) parent._children[parent._children.length - 1]._refresh();
        }
    }
}

dFTree.prototype.alter = function(arrayProps) {
    if (arrayProps['id']) {
        this.getNodeById(arrayProps['id']).alter(arrayProps);
    }
}

dFTree.prototype.getNodeById = function(nodeid) {
    return this._aNodes[this._searchNode(nodeid)];
}


dFTree.prototype._searchNode = function(id) {
    return this._searchNodeIn(id, this._aNodes);
}

dFTree.prototype._searchNodeIn = function(id, list) {
    var a = 0;
    for (a; a < list.length; a++) {
        if (list[a].id == id) {
            return a;
        }
    }
    return false;
}

dFTree.prototype._setCookie = function() {
    if (this.followCookies) {
        var state = "";
        first = true;
        var i;
        for (i in this._aNodes) {
            if (this._aNodes[i]._io) {
                if (first) {
                    first = false;
                }
                else {
                    state += ",";
                }
                state += this._aNodes[i].id;
            }
        }
        setCookie("cons" + this.name, state);
    }
}

dFTree.prototype._getCookie = function() {
    if (this.followCookies) {
        var state = getCookie("cons" + this.name);
        var start = 0;
        while (start < state.length) {
            var pos = state.indexOf(",", start);
            if (pos <= 0) pos = state.length;
            nodename = state.substring(start, pos);
            var node = this.getNodeById(nodename);
            if (node) {
                node._io = true;
            }
            start = pos + 1;
        }
    }
}

function getObjectById(name) {
    if (document.getElementById) {
        return document.getElementById(name);
    }
    else if (document.all) {
        return document.all[name];
    }
    else if (document.layers) {
        return document.layers[name];
    }
    return false;
}

function clearCookie(cookieName) {
    var now = new Date();
    var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
    this.setCookie(cookieName, 'cookieValue', yesterday);
    this.setCookie(cookieName, 'cookieValue', yesterday);
};

function setCookie(cookieName, cookieValue, expires, path, domain, secure) {
    document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

function getCookie(cookieName) {
    var cookieValue = '';
    var posName = document.cookie.indexOf(escape(cookieName) + '=');
    if (posName != -1) {
        var posValue = posName + (escape(cookieName) + '=').length;
        var endPos = document.cookie.indexOf(';', posValue);
        if (endPos != -1) {
            cookieValue = unescape(document.cookie.substring(posValue, endPos));
        }
        else {
            cookieValue = unescape(document.cookie.substring(posValue));
        }
    }
    return (cookieValue);
};

function imageHTML(src, attributes) {
    if (attributes === null) {
        attributes = '';
    }
    return "<img " + attributes + " src=\"" + src + "\">";
}

var global_ie = is_ie();
function is_ie() {
    var ua = window.navigator.userAgent.toLowerCase();
    if ((ua.indexOf('msie')) != -1) {
        return true;
    }
    else
        return false;
}