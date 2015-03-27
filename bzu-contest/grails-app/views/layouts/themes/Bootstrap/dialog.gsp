<!DOCTYPE html>
<html>
    <theme:head>
        <theme:layoutTemplate name="head"/>
    </theme:head>
    <theme:body>
    	<theme:layoutTemplate name="header" />
        <div class="container">
            <div class="row">
                <div class="content span6 offset3">
            		<theme:layoutZone name="page-header"/>
                    <theme:layoutZone name="body"/>
                </div>
            </div>
        </div>
        <theme:layoutTemplate name="footer"/>
    </theme:body>
</html>
