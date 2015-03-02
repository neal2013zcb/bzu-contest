<!DOCTYPE html>
<html>
    <theme:head>
        <theme:layoutTemplate name="head"/>
    </theme:head>
    <theme:body>
        <theme:layoutTemplate name="header"/>
        
        <div class="container">
            <div class="content">
                <div class="page-header">
                    <theme:layoutTitle/>
                </div>
                <div class="span12">
                    <theme:layoutZone name="secondary-navigation"/>
                </div>
                <div class="row">
                    <div class="span8">
                        <theme:layoutZone name="body"/>
                    </div>
                    <div class="span3">
                        <theme:layoutZone name="sidebar"/>
                    </div>
                </div>
            </div>
            <theme:layoutTemplate name="footer"/>
        </div>
    </theme:body>
</html>
