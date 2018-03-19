$(function(){
	montarTabelaEdicoes();
	listarEdicoes();
	
	$("#btnNovaEdicao").click(function(){
		salvarEdicao($("#formEdicao").serialize());
	});
	
});

function salvarEdicao(form){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/edicao/salvar',
		data: form,
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			if (data.id == 1) {
				var edicao = data.resultObject;
				window.location.href = UtilsJS.context_path() + '/noticias/edicao/'+edicao.id;
			} else if (data.id == 2) {
				criarAlert('informacaoEdicao', 'warning', data.mensagem);
			} else {
				criarAlert('informacaoEdicao', 'error', data.mensagem);
			}
		},
		error : function() {
			criarAlert('informacaoEdicao','error','Ocorreu um erro durante ao salvar a edição, por favor, tente novamente mais tarde.');
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function deletarEdicao(row){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/edicao/deletar',
		data: row,
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			listarEdicoes();
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}


function publicarEdicao(row){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/edicao/publicar',
		data: row,
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
				listarEdicoes();
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function listarEdicoes(){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'get',
		url : UtilsJS.context_path() + '/edicao/listar',
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			console.log(data.id);
			if (data.id == 1) {
				 $('#tabela_edicoes').bootstrapTable('load', data.resultObject);
			} else if (data.id == 2) {
				console.log(data.mensagem);
			} else {
				console.log(data.mensagem);
			}
		},
		error : function() {
			console.log("Erro");
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function montarTabelaEdicoes(){
	$('#tabela_edicoes').bootstrapTable('destroy');
    $('#tabela_edicoes').bootstrapTable({
        cache: false,
        striped: true,
        clickToSelect: true,
        pagination: true,
        pageSize: 7,
        pageList: [7],
        uniqueId: 'id',
        search: true,
        onDblClickRow: function (row, $element) {
        	if(row.idOrigemSof != 1)
        		editarLoja(row.id);
        	else
        		criarAlert('informacaoLoja', 'warning', 'Não é possível editar lojas que utilizam dados da VSM.');
        },
        columns: [{
            field: 'id',
            visible: false
        }, {
            field: 'ano',
            title: 'Ano',
            align: 'left',
            valign: 'middle'
        }, {
        	 field: 'mes',
             title: 'Mes',
             align: 'left',
             valign: 'middle'
        }, {
       	 	field: 'status',
       	 	title: 'Status',
       	 	align: 'left',
       	 	valign: 'middle',
       	 	formatter: statusFormatter
        },{
        	title: 'Editar',
        	align: 'center',
        	valign: 'middle',
        	formatter: editFormatter,
        	events: operateEvents
        }, {
	         title: 'Excluir',
	         align: 'center',
	         valign: 'middle',
	         formatter: removeFormatter,
	         events: operateEvents
		}, {
             title: 'Publicar',
             align: 'center',
             valign: 'middle',
             formatter: publishFormatter,
         	 events: operateEvents
        }]
    });
}

function statusFormatter(value, row, index){
	if(value === 'AGUARDANDO')
		return '<span class="label label-warning">'+value+'</span>';
	else
		return '<span class="label label-success">'+value+'</span>';
}

function editFormatter(value, row, index) {
    	return [
            '<a class="edit" href="javascript:void(0)" title="Clique para editar">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a>  ',
        ].join('');
}
function removeFormatter(value, row, index) {
	return [
        '<a class="remove" href="javascript:void(0)" title="Clique para excluir">',
        '<i class="fa fa-trash"></i>',
        '</a>  ',
    ].join('');
}
function publishFormatter(value, row, index) {
	return [
        '<a class="publish" href="javascript:void(0)" title="Clique para publicar">',
        '<i class="fa fa-bullhorn"></i>',
        '</a>  ',
    ].join('');
}
window.operateEvents = {
    'click .edit': function (e, value, row, index) {
    	console.log("Editar");
    	window.location.href = UtilsJS.context_path() + '/noticias/edicao/'+row.id;
    },
	'click .remove': function (e, value, row, index) {
		console.log("Remover");
		swal({
			  title: "Atenção",
			  text: "Realmente deseja deletar a edição ?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  deletarEdicao(row);
			  } 
			});
	},
    'click .publish': function (e, value, row, index) {
    	console.log("Publicar");
    	publicarEdicao(row);
    }
};