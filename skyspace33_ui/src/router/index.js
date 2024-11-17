import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airplanes from  '@/pages/Airplanes.vue';
import AirplaneDetail from  '@/pages/AirplaneDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import Destinations from  '@/pages/Destinations.vue';
import DestinationDetail from  '@/pages/DestinationDetail.vue';
import Citys from  '@/pages/Citys.vue';
import CityDetail from  '@/pages/CityDetail.vue';
import Routes from  '@/pages/Routes.vue';
import RouteDetail from  '@/pages/RouteDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import Pilots from  '@/pages/Pilots.vue';
import PilotDetail from  '@/pages/PilotDetail.vue';
import Rules from  '@/pages/Rules.vue';
import RuleDetail from  '@/pages/RuleDetail.vue';
import Checkins from  '@/pages/Checkins.vue';
import CheckinDetail from  '@/pages/CheckinDetail.vue';
import LoyaltyPrograms from  '@/pages/LoyaltyPrograms.vue';
import LoyaltyProgramDetail from  '@/pages/LoyaltyProgramDetail.vue';
import Memberships from  '@/pages/Memberships.vue';
import MembershipDetail from  '@/pages/MembershipDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import Feedbacks from  '@/pages/Feedbacks.vue';
import FeedbackDetail from  '@/pages/FeedbackDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airplanes',
		name: 'Airplanes',
		layout: DefaultLayout,
		component: Airplanes,
	},
	{
	    path: '/airplane/:airplaneId', 
	    name: 'AirplaneDetail',
		layout: DefaultLayout,
	    component: AirplaneDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/destinations',
		name: 'Destinations',
		layout: DefaultLayout,
		component: Destinations,
	},
	{
	    path: '/destination/:destinationId', 
	    name: 'DestinationDetail',
		layout: DefaultLayout,
	    component: DestinationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/citys',
		name: 'Citys',
		layout: DefaultLayout,
		component: Citys,
	},
	{
	    path: '/city/:cityId', 
	    name: 'CityDetail',
		layout: DefaultLayout,
	    component: CityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/routes',
		name: 'Routes',
		layout: DefaultLayout,
		component: Routes,
	},
	{
	    path: '/route/:routeId', 
	    name: 'RouteDetail',
		layout: DefaultLayout,
	    component: RouteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/pilots',
		name: 'Pilots',
		layout: DefaultLayout,
		component: Pilots,
	},
	{
	    path: '/pilot/:pilotId', 
	    name: 'PilotDetail',
		layout: DefaultLayout,
	    component: PilotDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/rules',
		name: 'Rules',
		layout: DefaultLayout,
		component: Rules,
	},
	{
	    path: '/rule/:ruleId', 
	    name: 'RuleDetail',
		layout: DefaultLayout,
	    component: RuleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkins',
		name: 'Checkins',
		layout: DefaultLayout,
		component: Checkins,
	},
	{
	    path: '/checkin/:checkinId', 
	    name: 'CheckinDetail',
		layout: DefaultLayout,
	    component: CheckinDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltyPrograms',
		name: 'LoyaltyPrograms',
		layout: DefaultLayout,
		component: LoyaltyPrograms,
	},
	{
	    path: '/loyaltyProgram/:loyaltyProgramId', 
	    name: 'LoyaltyProgramDetail',
		layout: DefaultLayout,
	    component: LoyaltyProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/memberships',
		name: 'Memberships',
		layout: DefaultLayout,
		component: Memberships,
	},
	{
	    path: '/membership/:membershipId', 
	    name: 'MembershipDetail',
		layout: DefaultLayout,
	    component: MembershipDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/feedbacks',
		name: 'Feedbacks',
		layout: DefaultLayout,
		component: Feedbacks,
	},
	{
	    path: '/feedback/:feedbackId', 
	    name: 'FeedbackDetail',
		layout: DefaultLayout,
	    component: FeedbackDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
