package com.ironreign.app.ui.pro

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ironreign.app.R

public class ProFeaturesFragmentDirections private constructor() {
  public companion object {
    public fun actionProFeaturesFragmentToArCoachFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_arCoachFragment)

    public fun actionProFeaturesFragmentToAiPlannerFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_aiPlannerFragment)

    public fun actionProFeaturesFragmentToComplexAIAnalysisFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_complexAIAnalysisFragment)

    public fun actionProFeaturesFragmentToOneRMCalculatorFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_oneRMCalculatorFragment)

    public fun actionProFeaturesFragmentToWearablesFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_wearablesFragment)

    public fun actionProFeaturesFragmentToChallengeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_proFeaturesFragment_to_challengeFragment)
  }
}
