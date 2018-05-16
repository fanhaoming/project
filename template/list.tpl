<#assign startT="$T.">
<#assign startT1="{$T.">
<#assign end="}">
<#assign forStart="{#foreach">
<#assign forEnd="{#/for}">
<div>
    <div class="row form-data-control table-search-container">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="form-horizontal row">
						<div class="col-sm-3">
							<div class="form-group">
								<label class="col-sm-3 control-label">姓名</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="name">
								</div>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="col-sm-3 control-label">性别</label>
								<div class="col-sm-9">
									<select class="form-control dict-select" name="sex" dict="Sex"></select>
								</div>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="row">
								<div class="col-sm-3 ">
									<a class="btn btn-sm btn-primary btn-search">搜索</a>
								</div>
								<div class="col-sm-3">
									<a class="btn btn-sm btn-reset btn-reset">重置</a>
								</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="position-relative table-loading-container">
        <script type="text/template">
            <table class="table table-striped table-bordered table-hover table-main-content">
                <thead>
                    <tr class="thcenter">
						<#list table.viewObjectColumns as column>
						<th>${column.comment}</th>
						</#list>
                    </tr>
                </thead>
                <tbody id="list">
                    ${forStart} ${startT}${table.instanceName}s as ${table.instanceName}${end}
                    <tr>
						<#list table.viewObjectColumns as column>
                        <td>${startT1}${table.instanceName}.${column.property}${end}</td>
						</#list>
                    </tr>
                    ${forEnd}
                </tbody>
            </table>
        </script>
        <div class="pager-container"></div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
		var page = new Page({
            initView : initView,
            initEvent : initEvent
        });

        function initView() {
            
        }
        
        function initEvent() {
            page.search(g.config.apiUrl + '/api/${table.instanceName}/search', "${table.className}");            
        }

        page.setup([{
            url: g.config.apiUrl + '/api/${table.instanceName}/search',
            data: {                
                pageSize: g.config.pageSize
            } 
        }]);
    });
</script>