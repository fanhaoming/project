<div>
    <div class="tabs-container">
        <div class="panel-body">
            <form class="form-inline">
				<div data-include="/page/${table.instanceName}/input.html"></div>
                <button type="button" class="save-info-btn btn btn-w-m btn-success center-block" style="font-family: '微软雅黑';font-weight:bold;">更 新</button>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        var page = new Page({
			initView : initView,
			initEvent : initEvent,
			bindData : function(){
				page.container.setFormData(page.data.${table.instanceName});
			}    	
        });
		
		function initView() {

        }
		
 
		function initEvent() {
            var btnSave = page.container.find('.save-info-btn');
            page.verify(btnSave, function() {
                var postData = page.container.getFormData();
				postData.id = page.params.query.id;
                ajax.postJson(g.config.apiUrl + '/api/${table.instanceName}/edit', postData, {
					callback : function(obj) {
						if(page.params.isDialog){
							dialog.close(page.params.index);						
						}
						page.params.callback(obj);
					}
				});
            });
        }

        page.setup([
			g.config.apiUrl + '/api/${table.instanceName}/get/' + page.params.query.id
		]);
    });
</script>