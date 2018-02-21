var UtilsJS = function(){
	return{
		context_path : function() {
			var pathArray = window.location.pathname.split('/');
			return pathArray[0];
		}
	}
}();