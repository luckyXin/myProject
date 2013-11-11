String.prototype.replaceCode = function(oldCode, newCode) {
	var _this = this;
	while (_this.indexOf(oldCode) != -1)
		_this = _this.replace(oldCode, newCode);
	return _this;
}
$$ = MyPlug = {
	createA : function(pstrText, pstrClass, peventCallback, pbHand, pjsonCss,
			pjsonAttrs) {
		var domA = document.createElement("a");
		if (undefined == pbHand || true == pbHand) {
			$(domA).attr("href", "javascript:void(0);");
		}
		$(domA).addClass(pstrClass);
		$(domA).text(pstrText);
		if (undefined != peventCallback) {
			$(domA).click(peventCallback);
		}
		if (undefined != pjsonCss) {
			for ( var x = 0; x < pjsonCss.length; x++) {
				$(domA).css(pjsonCss[x].name, pjsonCss[x].value);
			}
		}
		if (undefined != pjsonAttrs) {
			for ( var x = 0; x < pjsonAttrs.length; x++) {
				$(domA).attr(pjsonAttrs[x].name, pjsonAttrs[x].value);
			}
		}
		return domA;
	},
	createSpan : function(pstrText, pstrClass, peventCallback, pjsonCss) {
		var domSpan = document.createElement("span");
		$(domSpan).addClass(pstrClass);
		$(domSpan).text(pstrText);
		if (undefined != peventCallback) {
			$(domSpan).click(peventCallback);
		}
		if (undefined != pjsonCss) {
			for ( var x = 0; x < pjsonCss.length; x++) {
				$(domSpan).css(pjsonCss[x].name, pjsonCss[x].value);
			}
		}
		return domSpan;
	},
	createImg : function(pstrSrc, pstrClass, peventCallback, pjsonCss) {
		var domSpan = document.createElement("img");
		$(domSpan).addClass(pstrClass);
		$(domSpan).attr("src", pstrSrc);
		if (undefined != peventCallback) {
			$(domSpan).click(peventCallback);
		}
		if (undefined != pjsonCss) {
			for ( var x = 0; x < pjsonCss.length; x++) {
				$(domSpan).css(pjsonCss[x].name, pjsonCss[x].value);
			}
		}
		return domSpan;
	},
	createDiv : function(pstrID, pstrClass, pjsonCss, pstrText) {
		var domDiv = document.createElement("div");
		if (undefined != pstrClass)
			$(domDiv).addClass(pstrClass);
		if (undefined != pstrID)
			$(domDiv).attr("id", pstrID);
		if (undefined != pstrText)
			$(domDiv).html(pstrText);
		if (undefined != pjsonCss) {
			for ( var x = 0; x < pjsonCss.length; x++) {
				$(domDiv).css(pjsonCss[x].name, pjsonCss[x].value);
			}
		}
		return domDiv;
	}
}

$(document).ready(function() {
	tab = new $$.Tab.New();
});
(function($$) {
	function Tab(opts) {
		var _this = this;
		this.m_strContainerID;
		for (i in opts) {
			if (i.charAt(0) != '_') {
				eval('this.' + i + ' = opts[\'' + i + '\'];');
			}
		}
		this.m_alContainer = new Array();
		this.m_strNowShowOtherName = "";
		if (this.m_strContainerID != undefined) {
			bindEvent(_this);
		} else {
			init(_this);
		}
	}
	function init(_this) {
		var strShowOthername = "";
		if ($(".iframeCss").length > 0) {
			$(".iframeCss").each(function(i) {
				if (strShowOthername == "") {
					strShowOthername = $(this).attr("OtherName");
				}
				_this.m_alContainer[$(this).attr("OtherName")] = this;
			});
			_this.show(strShowOthername);
		}
	}
	function bindEvent(_this) {
		$("#" + _this.m_strContainerID + " > a").addClass("tab_select");
		$("#" + _this.m_strContainerID + " > a").click(function() {
			_this.select(this);
		});
		$("#" + _this.m_strContainerID + " > span").addClass("tab_select");
		$("#" + _this.m_strContainerID + " > span").click(function() {
			_this.select(this);
		});
	}
	function select(_this, obj) {
		$("#" + _this.m_strContainerID).find(".tab_selected").removeClass(
				"tab_selected");
		$(obj).addClass("tab_selected");
	}
	Tab.prototype = {
		select : function(obj) {
			if (this.m_strContainerID != undefined) {
				select(this, obj);
			}
		},
		show : function(pstrOtherName) {

			var _this = this;
			if (_this.m_strNowShowOtherName != "") {
				$(_this.m_alContainer[_this.m_strNowShowOtherName]).slideUp(
						"slow",
						function() {
							_this.m_strNowShowOtherName = pstrOtherName;
							$(_this.m_alContainer[pstrOtherName]).slideDown(
									"slow",
									function() {
										top.objMenu.addNav(pstrOtherName,
												function() {
													_this.show(pstrOtherName);
												});
										$(_this.m_alContainer[pstrOtherName])
												.find(".tabBack")
												.click(function() {
													top.objMenu.GoHistory();
													//top.objMenu.backNav(pstrOtherName);
													});
									});
						});
			} else {
				_this.m_strNowShowOtherName = pstrOtherName;
				$(_this.m_alContainer[pstrOtherName]).slideDown(
						"slow",
						function() {
							top.objMenu.addNav(pstrOtherName, function() {
								_this.show(pstrOtherName);
							});
							$(_this.m_alContainer[pstrOtherName]).find(
									".tabBack").click(function() {
								top.objMenu.GoHistory();
								//top.objMenu.backNav(pstrOtherName);
								});
						});
			}
		},
		Rename : function(pstrOtherName, pstrNewTitleName) {
			var _this = this;
			$(_this.m_alContainer[pstrOtherName]).find(".table_top > .text")
					.text(pstrNewTitleName);
		}
	};
	$$.Tab = {
		New : function(opts) {
			return new Tab(opts);
		}
	};
	return Tab;
})($$);

(function($$) {
	function Busy() {
		this.m_domBusy = create();
	}
	function create() {
		var odiv = document.createElement("DIV");
		document.body.insertBefore(odiv);
		$(odiv).addClass("Busy");
		return odiv;
	}
	function show(_this, strContainerID) {
		if (undefined != strContainerID) {
			$("#" + this.strContainerID).css("filter", "alpha(opacity=25)");
		}
		if (undefined != strContainerID) {
			$(_this.m_domBusy).get(0).style.left = $("#" + strContainerID)
					.offset().left
					+ ($("#" + strContainerID).get(0).offsetWidth / 2) - 73;
			$(_this.m_domBusy).get(0).style.top = $("#" + strContainerID)
					.offset().top
					+ ($("#" + strContainerID).get(0).offsetHeight / 2) - 20;
			$(_this.m_domBusy).show();
		} else {
			$(_this.m_domBusy).get(0).style.left = ($(document.body).get(0).clientWidth / 2) - 73;
			$(_this.m_domBusy).get(0).style.top = ($(document.body).get(0).clientHeight / 2) - 20;
			$(_this.m_domBusy).show();
		}
	}
	function hide(_this) {
		$(_this.m_domBusy).hide();
	}
	Busy.prototype = {
		show : function(strContainerID) {
			show(this, strContainerID);
		},
		hide : function() {
			hide(this);
		}

	};
	$$.Busy = {
		New : function() {
			return new Busy();
		}
	};
	return Busy;
})($$);
function AjaxError(XMLHttpRequest, textStatus, errorThrown) {
	var ErrorInfo = new Array();
	ErrorInfo["parsererror"] = "请求时分析器错误！";
	ErrorInfo["timeout"] = "请求超时，请检查网络后重试！";
	ErrorInfo["error"] = "请求时发生异常请检查后重试！";
	ErrorInfo["notmodified"] = "请求时网络异常请检查后重试！";
	top.messageBox.show(ErrorInfo[textStatus]);
}
