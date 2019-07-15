import Vue from 'vue';
import BootstrapVue, {BFormInput, BListGroup, BListGroupItem, BButton} from 'bootstrap-vue';

Vue.use(BootstrapVue);
Vue.component('b-button', BButton);
Vue.component('b-form-input', BFormInput);
Vue.component('b-list-group', BListGroup);
Vue.component('b-list-group-item', BListGroupItem);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import AppComponent from './component/app-component';

new Vue({
    el: "#app",
    render: h => h(AppComponent),
});