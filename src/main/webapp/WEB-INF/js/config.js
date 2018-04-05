require.config({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery/jquery-1.12.3.min',
        'handlebars': 'lib/handlebars-1.0.0',
        'layer1': 'lib/layer/layer',
        'laydate': 'lib/laydate/laydate',
        'pagination': 'lib/pagination/jquery.pagination',
        'jquery.validate': 'lib/validate/jquery.validate.min',
        'jquery.layout': 'lib/layout/jquery.layout-latest',
        'jquery.dataTables': 'lib/datatable/jquery.dataTables.min',
        'dataTables.bootstrap': 'lib/datatable/dataTables.bootstrap.min',
        'dataTables.fixedHeader': 'lib/datatable/dataTables.fixedHeader.min',
        'bootstrap': 'lib/bootstrap/js/bootstrap.min',
        'html5shiv.min': 'lib/bootstrap/js/html5shiv.min',
        'respond.min': 'lib/bootstrap/js/respond.min',
        'jquery.serialize': 'lib/jquery/jquery.serialize-object.min',
        'metisMenu': 'lib/metisMenu',
        'contabs': 'lib/contabs.min',
        'pace': 'lib/pace/pace.min',
        'select2': 'lib/select2/select2.min',
        'jquery.nanoscroller': 'lib/jquery/jquery.nanoscroller.min'
    },
    shim: {
        'bootstrap': {
            deps: ['jquery']
        },
        'layer1': {
            deps: ['jquery']
        },
        'pagination': {
            deps: ['jquery', 'lib/css!lib/pagination/pagination.css']
        },
        'laydate': {
            deps: ['jquery']
        },
        'jquery.layout': {
            deps: ['jquery']
        },
        'jquery.dataTables': {
            deps: ['jquery'],
        },
        'dataTables.bootstrap': {
            deps: ['jquery.dataTables', 'lib/css!lib/datatable/css/dataTables.bootstrap.min.css']
        },
        'metisMenu': {
            deps: ['jquery']
        },
        'contabs': {
            deps: ['jquery']
        },
        'select2': {
            deps: ['jquery']
        }
    },
    waitSeconds: 0
});