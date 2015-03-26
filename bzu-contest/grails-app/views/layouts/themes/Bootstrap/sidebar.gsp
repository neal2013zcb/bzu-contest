<!DOCTYPE html>
<html>
    <theme:head>
        <theme:layoutTemplate name="head"/>
    </theme:head>
    <theme:body>
        <theme:layoutTemplate name="header"/>
        
        <div class="container">
            <div class="content">
                <div class="row-fluid">
                    <div class="span3">
                        <theme:layoutZone name="sidebar"/>
                    </div>
                    <div class="span8">
            			<theme:layoutZone name="page-header"/>
		                <theme:layoutZone name="secondary-navigation"/>
                        <theme:layoutZone name="body"/>
                    </div>
                </div>
            </div>
            <theme:layoutTemplate name="footer"/>
        </div>
    </theme:body>
</html>
