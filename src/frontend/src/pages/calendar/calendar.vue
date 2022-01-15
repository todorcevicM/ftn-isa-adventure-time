<template>

    <calendar-view
        :show-date="showDate"
        :events="events"
        class="
            theme-default
            holiday-us-traditional holiday-us-official
        "
    >
        <template #header="{ headerProps }" @click-event="onClickEvent">
            
            <calendar-view-header
                :header-props="headerProps"
                @input="setShowDate"
            />
        </template>
    </calendar-view>

</template>


<script>
// import { ref } from "vue";
// import axios from "axios";
import { CalendarView, CalendarViewHeader } from "vue-simple-calendar";
import "../../../node_modules/vue-simple-calendar/dist/style.css";
export default {
	components: {
		CalendarView,
		CalendarViewHeader,
	},
	setup() {
        
        return {
            showDate: new Date(),
            setShowDate(d) {
                this.showDate = d;
            },
            events: [
                {
                    id: "e1",
                    startDate: "2022-01-01",
                    endDate: "2018-01-02",
                    title: "Sample event 1",
                },
            ],
            onClickEvent() {
                alert('event clicked')
            },
        }
    },
}
</script>


<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");
body {
    background-color: #e6e4df;
    background-size: 100%;
    background-repeat: no-repeat;
    color: #10120e;
    font-family: Aleo;
}



</style>